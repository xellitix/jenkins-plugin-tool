package com.xellitix.jenkins.plugintool.api.auth;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * API authentication module.
 *
 * @author Grayson Kuhns
 */
public class ApiAuthenticationModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // JenkinsApiUser factory
    install(new FactoryModuleBuilder()
        .implement(JenkinsApiUser.class, DefaultJenkinsApiUser.class)
        .build(JenkinsApiUserFactory.class));
  }
}
