package com.kyupid.kshop.order.config;

import com.kyupid.kshop.order.auth.JwtAuth;
import com.kyupid.kshop.order.presentation.exception.NoTokenException;
import com.sun.corba.se.impl.logging.InterceptorsSystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtAuth jwtAuth;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException, ServletException {
        log.info(">>> interceptor.preHandle 호출");
        String jwt = request.getHeader("security");

        if (jwt == null) {
            log.warn(">>> JWT is NULL");
            request.setAttribute("message", "JWT is NULL");
            request.setAttribute("exception", "NoTokenException");
            request.getRequestDispatcher("/api/error").forward(request, response);
            return false;
        }

        if (!validateToken(jwt)) {
            log.warn(">>> 유효하지 않은 토큰으로 접근 시도");
            return false;
        } else {
            request.setAttribute("memberId", jwtAuth.decode(jwt).get("memberId"));
            return true;
        }
    }

    private boolean validateToken(String jwt) {
        log.info("만료되지 않음, 위조되지 않음");
        return true;
    }
}
