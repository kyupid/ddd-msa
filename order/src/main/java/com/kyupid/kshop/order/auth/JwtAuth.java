package com.kyupid.kshop.order.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuth {

    private final JwtKey key;

    public Claims decode(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key.keyEncrypted())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
