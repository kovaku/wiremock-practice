package com.github.kovaku.transformers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class StandalonePropertyLoaderResponseDefinitionTransformer extends ResponseTemplateTransformer {

  private static final String DEFAULT_PROPERTY_FILE_NAME = "application.properties";
  private static final String NAME = "property";

  private static final Helper<String> PROPERTY_HELPER = new Helper<String>() {
    @Override
    public Object apply(String context, Options options) throws IOException {
      String file = options.hash("file", null);
      String fileName = (Objects.nonNull(file)) ? file : DEFAULT_PROPERTY_FILE_NAME;
      FileInputStream propFile = new FileInputStream(getClass().getClassLoader().getResource(fileName).getFile());
      Properties properties = new Properties(System.getProperties());
      properties.load(propFile);
      return properties.get(context);
    }
  };

  public StandalonePropertyLoaderResponseDefinitionTransformer() {
    super(true, NAME, PROPERTY_HELPER);
  }

  @Override
  public String getName() {
    return NAME;
  }
}
