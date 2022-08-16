package com.kyupid.kshop.order.infra;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestClient {
    private static RestTemplate restTemplate;
    private static final String API_KEY = "HELLOWORLD";

    /**
     * Request, Response 에는 같은 DTO를 쓴다
     * Generic 활용해서 재활용성 높이기
     */
    protected static <T> T process(T body, String requestUrl) {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
//        factory.setReadTimeout(5000); // 타임아웃 설정 5초

        restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<T> entity = new HttpEntity<>(body, headers);

        ResponseEntity<T> response = restTemplate.exchange(requestUrl, HttpMethod.POST, entity, new ParameterizedTypeReference<T>(){});
        System.out.println("response: " + response);
        return response.getBody();
    }
}
