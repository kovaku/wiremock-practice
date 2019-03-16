package com.github.kovaku.config;

import com.github.kovaku.runner.WireMockRunner;
import com.github.kovaku.transformers.StandalonePropertyLoaderResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.github.kovaku")
public class ApplicationConfig {

  @Bean
  public ResponseTemplateTransformer propertyLoaderResponseDefinitionTransformer() {
    return new StandalonePropertyLoaderResponseDefinitionTransformer();
  }

  @Bean
  public WireMockRunner wireMockRunner() {
    return new WireMockRunner();
  }

  @Bean(destroyMethod = "stop")
  public WireMockServer wireMockServer() {
    return wireMockRunner().getWireMockServer();
  }
}
