package com.kyupid.kshop.order.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
    public Long getMemberId(){
        // 스레드에 바인딩된 Request를 들고와서,
        // 서블릿 요청 및 HTTP 세션 범위에있는 ServletRequestAttributes로 캐스트하여
        // HttpServerltRequest를 들고온다
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return Long.valueOf(request.getAttribute("memberId").toString());
    }
}
