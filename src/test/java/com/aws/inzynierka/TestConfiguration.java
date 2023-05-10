package com.aws.inzynierka;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
