package com.xellitix.jenkins.plugintool.authentication;

import com.google.inject.AbstractModule;

/**
 * Jenkins authentication module.
 *
 * @author Garrett Ewens
 */
public class JenkinsAuthenticationModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // Jenkins API token retriever
    bind(JenkinsApiTokenRetriever.class).to(DefaultJenkinsApiTokenRetriever.class);
  }
}
