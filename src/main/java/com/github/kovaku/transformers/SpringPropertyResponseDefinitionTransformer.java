package com.github.kovaku.transformers;

import com.github.kovaku.support.SpringEmbeddedValueResolver;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringPropertyResponseDefinitionTransformer extends ResponseDefinitionTransformer {

  public static final String NAME = "SpringPropertyResponseTemplateTransformer";

  @Autowired
  private SpringEmbeddedValueResolver springEmbeddedValueResolver;

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
    ResponseDefinitionBuilder newResponseDefBuilder = ResponseDefinitionBuilder.like(responseDefinition);
    newResponseDefBuilder.withBody(springEmbeddedValueResolver.resolveProperty(responseDefinition.getBody()));
    return newResponseDefBuilder.build();
  }
}
