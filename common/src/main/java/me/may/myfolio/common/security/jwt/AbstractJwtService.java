package me.may.myfolio.common.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultJwtParserBuilder;
import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import me.may.myfolio.common.security.Role;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractJwtService {

    private final String SECRET_KEY = "G7RtBxU4fVQx9z7vT1iX0WzQEQBmcCKb";
    private static final String EXPIRATION_TIME = "86400";

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) throws IllegalAccessException {
        try {
            return parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        } catch (JwtException | IllegalArgumentException exception) {
            throw new IllegalAccessException("Invalid token: " + exception.getMessage());
        }
    }

    public boolean isValid(String token) {
        try {
            Claims claims = this.getAllClaimsFromToken(token);
            return claims.getExpiration().after(new Date());
        } catch (IllegalAccessException e) {
            return true;
        }
    }

    public String generateToken(String email, long userId, Role role, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("role", role);
        return generate(claims, email, type);
    }

    private String generate(Map<String, Object> claims, String username, String type) {
        long expirationTimeLong;
        if ("ACCESS".equals(type)) {
            expirationTimeLong = Long.parseLong(EXPIRATION_TIME) * 1000;
        } else {
            expirationTimeLong = Long.parseLong(EXPIRATION_TIME) * 1000 * 5;
        }
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    private static JwtParserBuilder parser() {
        return new DefaultJwtParserBuilder();
    }
}
