package com.omen.learning.common.utils;

import com.omen.learning.common.support.JwtHolder;
import com.weweibuy.framework.common.core.exception.BusinessException;
import com.weweibuy.framework.common.core.model.eum.CommonErrorCodeEum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.Date;
import java.util.UUID;

/**
 * @author : Knight
 * @date : 2021/3/29 4:25 下午
 */
@Slf4j
@Component
public class JwtUtilsPro {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private static final String JWT_PREDIX = "X-CAT";
    private static final long JWT_EXPIRED = 30 * 24 * 60 * 60 * 1000L;
    private static final String USER_ID_KEY = "user_id";

    public String generateCatToken(String userId, String clientType) {
        return JWT_PREDIX + generateJwt(userId, clientType);
    }

    private String generateJwt(String userUUID, String clientType) {
        Claims claims = Jwts.claims();
        // issuer
        claims.setIssuer(JwtHolder.PRIVATE_KEY);
        // aud
        claims.setAudience(clientType);
        // sub
        claims.setSubject(userUUID);
        long current = System.currentTimeMillis();
        long expire = current + JWT_EXPIRED;
        // iat
        claims.setIssuedAt(new Date(current));
        // exp
        claims.setExpiration(new Date(expire));
        // jti
        claims.setId(UUID.randomUUID().toString());
        // userid
        claims.put(USER_ID_KEY, "");

        return Jwts.builder()
                .signWith(SignatureAlgorithm.ES256, privateKey)
                .setHeaderParam(JwsHeader.KEY_ID, JwtHolder.KID)
                .setClaims(claims)
                .compact();
    }

    /**
     * jwtUtils 生成的jwt更安全
     */
    @PostConstruct
    public void init() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Reader rdr = new StringReader(JwtHolder.PRIVATE_KEY);
            Object parsed = new org.bouncycastle.openssl.PEMParser(rdr).readObject();
            KeyPair pair = new org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter().getKeyPair((org.bouncycastle.openssl.PEMKeyPair) parsed);
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodeEum.FORBIDDEN);
        }
    }


}