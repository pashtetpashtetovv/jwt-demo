package com.example.jwtdemo.config.security.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${app-properties.jwtSecret}")
    private String jwtSecret;

    @Value("${app-properties.jwtExpirationMs}")
    private String jwtExpirationMs;

    private Key key = null;

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    public String generateGwtToken(Authentication authentication) {
        var jwtExpirationMsLong = Long.parseLong(jwtExpirationMs);
        var nowInstant = Instant.now();
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(Date.from(nowInstant))
                .setExpiration(Date.from(nowInstant.plusMillis(jwtExpirationMsLong)))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        if (this.key == null) {
            key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        }
        return key;
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parse(token);
            return true;
        } catch (JwtException e){
            log.error("Token exception - {}", e.getMessage());
            return false;
        }
    }
}
