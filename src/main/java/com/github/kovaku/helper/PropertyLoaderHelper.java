package com.github.kovaku.helper;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoaderHelper implements Helper<String> {

  private static final String DEFAULT_PROPERTY_FILE_NAME = "application.properties";

  @Override
  public Object apply(String context, Options options) throws IOException {
    String file = options.hash("file", null);
    String fileName = (Objects.nonNull(file)) ? file : DEFAULT_PROPERTY_FILE_NAME;
    FileInputStream propFile = new FileInputStream(getClass().getClassLoader().getResource(fileName).getFile());
    Properties properties = new Properties(System.getProperties());
    properties.load(propFile);
    return properties.get(context);
  }
}
