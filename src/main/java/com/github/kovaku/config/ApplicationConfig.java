package com.github.kovaku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.kovaku.runner.WireMockRunner;
import com.github.tomakehurst.wiremock.WireMockServer;

@Configuration
@ComponentScan(basePackages = "com.github.kovaku")
public class ApplicationConfig {

    @Bean
    public WireMockRunner wireMockRunner() {
        return new WireMockRunner();
    }

    @Bean(destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return wireMockRunner().getWireMockServer();
    }
}
