package org.qxdn.birthdayreminder.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;

import java.util.Date;

public class JWTUtils {

    private static final Integer EXPIRED_DAY = 10;

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(BirthdayConstants.JWT_SECRET);

    public static String generateToken(Long userId){
        Date now = DateUtils.now();
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(DateUtils.addDays(now, EXPIRED_DAY))
                .withIssuer(BirthdayConstants.JWT_ISSUER)
                .withClaim("id", userId)
                .sign(ALGORITHM);
    }


    /**
     * 解码token
     * @param token
     * @return userId
     */
    public static Long verifyToken(String token){
        DecodedJWT decodedJWT =JWT.require(ALGORITHM)
                .withIssuer(BirthdayConstants.JWT_ISSUER)
                .build()
                .verify(token);
        return decodedJWT.getClaim("id").asLong();
    }
}
