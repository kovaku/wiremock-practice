package com.github.kovaku.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

public class WireMockRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(WireMockRunner.class);

    @Value("${server.port:8080}")
    private Integer wiremockPort;

    private WireMockServer wireMockServer;

    public WireMockServer getWireMockServer() {
        if (wireMockServer == null) {
            WireMockConfiguration wireMockConfiguration = WireMockConfiguration.options();

            wireMockConfiguration.port(wiremockPort);
            wireMockConfiguration.usingFilesUnderClasspath("definitions");
            wireMockConfiguration.extensions(new ResponseTemplateTransformer(true));

            wireMockServer = new WireMockServer(wireMockConfiguration);
            LOGGER.info("WireMock server is starting at port: {}", wiremockPort);
            wireMockServer.start();
        }
        return wireMockServer;
    }
}
