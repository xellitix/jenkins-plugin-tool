package com.xellitix.jenkins.plugintool.http;

import com.google.inject.AbstractModule;

/**
 * HTTP module.
 *
 * @author Grayson Kuhns
 */
public class HttpModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // HttpClient provider
    bind(HttpClientProvider.class).to(ApacheHttpClientProvider.class);

    // HttpGet factory
    bind(HttpGetFactory.class).to(ApacheHttpGetFactory.class);
  }
}
