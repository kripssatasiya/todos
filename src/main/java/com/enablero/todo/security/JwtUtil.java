package com.enablero.todo.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class JwtUtil {

    private static final String GOOGLE_JWKS_URL = "https://www.googleapis.com/oauth2/v3/certs";
    private static final String CLIENT_ID = "64211308037-7angnaompl00a9f9gq1p3g995t01l6tt.apps.googleusercontent.com";

    // 64211308037-7angnaompl00a9f9gq1p3g995t01l6tt.apps.googleusercontent.com - client id
    // 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 - secrete key
    // creation date - 14 October 2024 at 17:57:26 GMT+5

    public String extractEmail(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validateGoogleToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            JWKSet jwkSet = JWKSet.load(new URL(GOOGLE_JWKS_URL));

            String kid = signedJWT.getHeader().getKeyID();
            JWK jwk = jwkSet.getKeyByKeyId(kid);

            if (jwk == null) {
                throw new SecurityException("Invalid token key ID");
            }


            boolean validSignature = signedJWT.verify(new com.nimbusds.jose.crypto.RSASSAVerifier((com.nimbusds.jose.jwk.RSAKey) jwk));


            if (!validSignature) {
                return false;
            }


            String issuer = signedJWT.getJWTClaimsSet().getIssuer();
            String audience = signedJWT.getJWTClaimsSet().getAudience().get(0);

            return "https://accounts.google.com".equals(issuer) && CLIENT_ID.equals(audience);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

