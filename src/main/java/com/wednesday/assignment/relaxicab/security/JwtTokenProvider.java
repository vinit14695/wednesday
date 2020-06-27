package com.wednesday.assignment.relaxicab.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wednesday.assignment.relaxicab.service.exception.GenericException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * This class is used in the project to provide JWT token after successful authentication
 */
public class JwtTokenProvider {
    private static final String TOKEN_ISSUER = "https://relaxicab.com";

    private final Algorithm algorithm;

    public JwtTokenProvider(final String secret) throws GenericException {
        try {
            algorithm = Algorithm.HMAC512(secret);
        } catch (IllegalArgumentException e) {
            throw new GenericException("GEN-001", "Unexpected Exception occurred.");
        }
    }


    /**
     * This method receives uuid of the user, current time and expiry time of the access token.
     * This information is stored in the payload of the JWT token and the JWT token is returned by this method.
     */
    /**
     * @param userUuid        - uuid of the user
     * @param issuedDateTime  - current time
     * @param expiresDateTime - expiry time of the JWT token
     * @return - generated JWT token
     */
    public String generateToken(final long userUuid, final Timestamp issuedDateTime, final Timestamp expiresDateTime) {

        final Date issuedAt = new Date(issuedDateTime.getNanos());
        final Date expiresAt = new Date(expiresDateTime.getNanos());

        return JWT.create().withIssuer(TOKEN_ISSUER)
                .withKeyId(UUID.randomUUID().toString())
                .withAudience(Long.toString(userUuid))
                .withIssuedAt(issuedAt).withExpiresAt(expiresAt).sign(algorithm);
    }

}
