package com.kyupid.kshop.member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuth {
    private final JwtKey key;

    public String encode(String id) {
        return Jwts.builder()
                .signWith(key.keyEncrypted())
                .claim("memberId", id)
                .compact();
    }

    public Claims decode(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key.keyEncrypted())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
