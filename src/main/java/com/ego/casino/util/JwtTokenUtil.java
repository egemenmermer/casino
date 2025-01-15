package com.ego.casino.util;

import com.ego.casino.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final long TOKEN_VALIDITY = 1000L * 60 * 60 * 24 * 7;
    private static final SignatureAlgorithm SIGNING_ALGORITHM = SignatureAlgorithm.HS512;
    private final SecretKey secret = Keys.secretKeyFor(SIGNING_ALGORITHM);

    public String getEmailFromToken(String token) {
        try {
            return getClaimFromToken(token, Claims::getSubject);
        } catch (Exception e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(currentDate());
    }

    private Date currentDate() {
        return new Date(System.currentTimeMillis());
    }

    public String generateToken(CustomUserDetails usersDetail) {
        return Jwts.builder()
                .setSubject(usersDetail.getEmail())
                .setIssuedAt(currentDate())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SIGNING_ALGORITHM, secret)
                .compact();
    }

    public Boolean validateToken(String token, CustomUserDetails usersDetail) {
        final String email = getEmailFromToken(token);
        return (email.equals(usersDetail.getEmail()) && !isTokenExpired(token));
    }

    public String extractEmail(String token) {
        return getEmailFromToken(token);
    }
}