package com.example.learning_intern.component.jwt;

import com.example.learning_intern.component.customUser.CustomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long EXPIRATION_TIME = 860_000_000;
    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        CustomUserDetail user = (CustomUserDetail) authentication.getPrincipal();

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("userID", user.getUserID());

        return Jwts.builder()
                .setIssuedAt(now)
                .setClaims(claims)
                .setExpiration(expirationDate)
                .setSubject(user.getEmail())
                .signWith(SECRET_KEY,SignatureAlgorithm.HS512).compact();
    }

    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    public Integer getUserID(String token){
        return getAllClaims(token).get("userID", Integer.class);
    }

    public String getRole(String token){
        return getAllClaims(token).get("roles", String.class);
    }
        public List<SimpleGrantedAuthority> getAuthorities(String token) {
            Claims claims = getAllClaims(token);
            List<String> roles = claims.get("roles", List.class); // Sử dụng key 'roles'
            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }


    public String getEmail(String token) {
        return getAllClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;

        }
    }
}
