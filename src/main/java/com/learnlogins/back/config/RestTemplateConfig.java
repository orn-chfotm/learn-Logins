package com.learnlogins.back.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
//
//    @Bean
//    CloseableHttpClient httpClient() {
//        CloseableHttpClient httpClient = HttpClientBuilder.create()
//                .setMaxConnTotal(100)
//                .setMaxConnPerRoute(30)
//                .build();
//
//        return httpClient;
//    }
//
//    @Bean
//    HttpComponentsClientHttpRequestFactory factory(CloseableHttpClient httpClient) {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(5000);
//        factory.setConnectTimeout(3000);
//        factory.setHttpClient(httpClient);
//
//        return factory;
//    }
//
//    @Bean
//    RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory) {
//        return new RestTemplate(factory);
//    }
}
