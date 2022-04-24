package com.travix.medusa.busyflights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        return restTemplate;
    }
}
