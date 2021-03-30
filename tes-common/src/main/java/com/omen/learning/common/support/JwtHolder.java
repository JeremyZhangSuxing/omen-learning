package com.omen.learning.common.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : Knight
 * @date : 2021/3/29 4:25 下午
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtHolder {
    /**
     * The PEM encoded EC private key.
     */
    public static final String PRIVATE_KEY = "knight";
    /**
     * The JWT <a href="https://tools.ietf.org/html/rfc7519#section-4.1.1">iss</a>
     * (issuer) claim.
     * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata">issuer</a>
     */
    public static final String ISSUER = "tes";
    /**
     * claim  header
     */
    public static final String KID = "harden";

}
