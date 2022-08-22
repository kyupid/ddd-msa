package com.kyupid.kshop.order.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class RestClient {

    private final RestTemplate restTemplate;

    private static final String REQUEST_URL = "http://product-service";

    private static final String API_KEY = "HELLOWORLD";


    /**
     * Generic 활용해서 재활용성 높이기
     */
    protected <T, S> S postExchange(T body, String requestURI, ParameterizedTypeReference<S> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<T> entity = new HttpEntity<>(body, headers);
        ResponseEntity<S> response = restTemplate.exchange(REQUEST_URL + requestURI, HttpMethod.POST, entity, responseType);
        log.info("response postExchange: " + response);
        return response.getBody();
    }

    protected <T, S> S putExchange(T body, String requestURI, ParameterizedTypeReference<S> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<T> entity = new HttpEntity<>(body, headers);
        ResponseEntity<S> response = restTemplate.exchange(REQUEST_URL + requestURI, HttpMethod.PUT, entity, responseType);
        log.info("response putExchange: " + response);
        return response.getBody();
    }
}
