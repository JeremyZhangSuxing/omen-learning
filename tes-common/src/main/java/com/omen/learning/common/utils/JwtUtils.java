package com.omen.learning.common.utils;

import com.omen.learning.common.enums.TokenState;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * @author : Knight
 * @date : 2021/1/12 11:36 上午
 */
@Slf4j
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {
    /**
     * JWT 加解密类型
     */
    private static final SignatureAlgorithm JWT_ALG = SignatureAlgorithm.HS256;
    /**
     * JWT 生成密钥使用的密码
     */
    private static final String JWT_RULE = "Jeremy";

    /**
     * JWT 添加至HTTP HEAD中的前缀
     */
    private static final String JWT_SEPARATOR = "X-CAT ";

    /**
     * 密钥：通过生成 JWT_ALG 和 JWT_RULE 加密生成
     */
    private static final Key SECRET = generateKey();

    /**
     * 生成秘钥
     */
    private static SecretKey generateKey() {
        // 将密钥生成键转换为字节数组
        byte[] bytes = Base64.decodeBase64(JWT_RULE);
        // 根据指定的加密方式，生成密钥
        return new SecretKeySpec(bytes, JWT_ALG.getJcaName());
    }


    public static String buildJWT(String iss, Integer duration, String tid) {
        return generaJwtToken(iss, new Date(), duration, tid);
    }


    /**
     * @param iss      签发人
     * @param nbf      生效时间
     * @param duration 失效配置
     * @param tid      业务请求uuid 防止token被滥用
     * @return jwtToken
     */
    private static String generaJwtToken(String iss, Date nbf, Integer duration, String tid) {
        // jwt的签发时间
        long issAt = System.currentTimeMillis();
        // jwt的过期时间，这个过期时间必须要大于签发时间
        long exp = 0;
        if (duration != null) {
            exp = (nbf == null ? (issAt + expireTime(duration)) : (nbf.getTime() + expireTime(duration)));
        }
        // 获取JWT字符串
        String compact = Jwts.builder()
                .signWith(JWT_ALG, SECRET)
                .setId(tid)
                .setIssuer(iss)
                .setNotBefore(nbf)
                //签发时间
                .setIssuedAt(new Date(issAt))
                //过期时间
                .setExpiration(new Date(exp))
                .compact();
        // 在JWT字符串前添加"Bearer "字符串，用于加入"Authorization"请求头
        return JWT_SEPARATOR + compact;
    }

    /**
     * 校验jwt
     *
     * @param token  token
     * @param issUre 签发人
     * @return 校验结果
     */
    public static TokenState validateJWT(String token, String issUre, String uuid) {
        try {
            Claims claims = getClaimsFromToken(token);
            log.info("token param {}", claims.toString());
            if (!claims.getIssuer().equals(issUre) || !claims.getId().equals(uuid) || claims.getExpiration().before(new Date())) {
                return TokenState.INVALID;
            }
        } catch (ExpiredJwtException e) {
            // 仅仅是token过期异常直接返回false
            log.info("token expire {}", issUre);
            return TokenState.EXPIRED;
        }
        return TokenState.VALID;
    }

    private static Claims getClaimsFromToken(String token) {
        // 移除 JWT 前的前缀字符串
        token = StringUtils.substringAfter(token, JWT_SEPARATOR);
        // 解析 JWT 字符串
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 有效期时间 15
     */
    private static long expireTime(Integer duration) {
        return duration * 1500 * 1000L;
    }
}
