package com.kyupid.kshop.order.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebMvcConfigure implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        log.info(">>> 인터셉터 등록");
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(authenticationInterceptor);
        interceptorRegistration.addPathPatterns("/api/**");
        interceptorRegistration.excludePathPatterns("/api/error");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8888")
                .allowedMethods(HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name());
    }
}
