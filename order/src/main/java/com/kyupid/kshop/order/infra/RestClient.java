package com.kyupid.kshop.order.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@Component
public class RestClient {
    private static final RestTemplate restTemplate;
    private static final String API_KEY = "HELLOWORLD";

    static {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5)) // 타임아웃 설정 5초
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    /**
     * Generic 활용해서 재활용성 높이기
     */
    protected static <T, S> S postExchange(T body, String requestUrl, ParameterizedTypeReference<S> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<T> entity = new HttpEntity<>(body, headers);
        ResponseEntity<S> response = restTemplate.exchange(requestUrl, HttpMethod.POST, entity, responseType);
        log.info("response: " + response);
        return response.getBody();
    }

    protected static <T, S> S putExchange(T body, String requestUrl, ParameterizedTypeReference<S> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<T> entity = new HttpEntity<>(body, headers);
        ResponseEntity<S> response = restTemplate.exchange(requestUrl, HttpMethod.PUT, entity, responseType);
        log.info("response: " + response);
        return response.getBody();
    }
}
