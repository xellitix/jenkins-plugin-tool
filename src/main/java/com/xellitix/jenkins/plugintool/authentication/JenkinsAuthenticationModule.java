package com.xellitix.jenkins.plugintool.authentication;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

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
    bind(JenkinsApiUserRetriever.class).to(DefaultJenkinsApiUserRetriever.class);

    // JenkinsApiUser factory
    install(new FactoryModuleBuilder()
        .implement(JenkinsApiUser.class, DefaultJenkinsApiUser.class)
        .build(JenkinsApiUserFactory.class));
  }
}
