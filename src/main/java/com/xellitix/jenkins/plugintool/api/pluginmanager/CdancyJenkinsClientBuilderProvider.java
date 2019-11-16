package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.cdancy.jenkins.rest.JenkinsClient;
import javax.inject.Singleton;

/**
 * Cdancy {@link JenkinsClientBuilderProvider} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class CdancyJenkinsClientBuilderProvider implements JenkinsClientBuilderProvider {

  /**
   * Gets a {@link JenkinsClient.Builder}.
   *
   * @return The {@link JenkinsClient.Builder}.
   */
  @Override
  public JenkinsClient.Builder get() {
    return JenkinsClient.builder();
  }
}
