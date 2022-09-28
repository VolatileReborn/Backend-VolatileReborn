package com.example.BackendVolatile.config;

import org.springframework.aop.framework.ProxyConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {



    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(1000000);
        httpRequestFactory.setConnectTimeout(1000000);
        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
        return new RestTemplate(simpleClientHttpRequestFactory);
    }
}
