package com.ystan.schedule.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.assertj.core.util.DateUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JSONWebTokenUtils {

    /**
     * random secret key
     */
    private final String KEY = UUID.randomUUID().toString();
    /**
     * signature algo
     */
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    /**
     * 15 mins in milis
     */
    private final long TOKEN_EXPIRATION_PERIOD = 1000 * 60 * 15;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return buildToken(claims, userDetails.getUsername());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return !isTokenExpired(token) && username.equals(userDetails.getUsername());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private String buildToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + TOKEN_EXPIRATION_PERIOD))
                .setSubject(subject)
                .signWith(signatureAlgorithm, KEY)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(DateUtil.now());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
