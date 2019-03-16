package com.github.kovaku.config;

import com.github.jknack.handlebars.Helper;
import com.github.kovaku.helper.PropertyLoaderHelper;
import com.github.kovaku.runner.WireMockRunner;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.github.kovaku")
public class ApplicationConfig {

  @Bean
  public Helper<String> propertyLoaderHelper() {
    return new PropertyLoaderHelper();
  }

  @Bean
  public ResponseTemplateTransformer responseTemplateTransformerWithHandler() {
    return new ResponseTemplateTransformer(true, "property", propertyLoaderHelper());
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
