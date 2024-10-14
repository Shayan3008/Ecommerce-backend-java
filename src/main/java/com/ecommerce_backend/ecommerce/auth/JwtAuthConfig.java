package com.ecommerce_backend.ecommerce.auth;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;



import org.springframework.stereotype.Component;

import com.ecommerce_backend.ecommerce.auth.models.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtAuthConfig {
    private static final SecretKey SECRET_KEY =  Keys.hmacShaKeyFor("G3rd6sf5Kx8vQXYfMBU4XRAOZX/2hGrUhnVytpRy0cU=".getBytes());
    private long accessTokenValidity = 60 * 60l * 1000;

    private JwtParser jwtParser;

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public JwtAuthConfig() {
        this.jwtParser = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build();
    }

    public String createToken(Users user) {
        Claims claims = Jwts.claims().setSubject(user.getName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SECRET_KEY,SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) {

        return claims.getExpiration().after(new Date());

    }

    public String getName(Claims claims) {
        return claims.getSubject();
    }

}
