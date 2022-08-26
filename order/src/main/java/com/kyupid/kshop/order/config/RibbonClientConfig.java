package com.kyupid.kshop.order.config;

import com.kyupid.kshop.order.infra.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * order -> product 서비스 호출에 대해 로드밸런싱을 수행한다
 */
@Configuration
@LoadBalancerClient(name = "product-service")
public class RibbonClientConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }
}
