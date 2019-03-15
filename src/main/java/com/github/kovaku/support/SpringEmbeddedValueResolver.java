package com.github.kovaku.support;

import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.stereotype.Service;

@Service
public class SpringEmbeddedValueResolver extends EmbeddedValueResolutionSupport {

  public String resolveProperty(String property) {
    return resolveEmbeddedValue(property);
  }
}
