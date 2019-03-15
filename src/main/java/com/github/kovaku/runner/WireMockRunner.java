package com.github.kovaku.runner;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class WireMockRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(WireMockRunner.class);

    @Autowired
    private List<ResponseDefinitionTransformer> transformers;

    @Value("${server.port:8080}")
    private Integer wiremockPort;

    private WireMockServer wireMockServer;

    public WireMockServer getWireMockServer() {
        if (wireMockServer == null) {
            WireMockConfiguration wireMockConfiguration = WireMockConfiguration.options();

            wireMockConfiguration.port(wiremockPort);
            wireMockConfiguration.usingFilesUnderClasspath("definitions");
            transformers.forEach(wireMockConfiguration::extensions);

            wireMockServer = new WireMockServer(wireMockConfiguration);
            LOGGER.info("WireMock server is starting at port: {}", wiremockPort);
            wireMockServer.start();
        }
        return wireMockServer;
    }
}
