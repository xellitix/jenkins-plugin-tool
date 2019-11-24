package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import java.net.URI;

/**
 * {@link JenkinsClient} factory.
 *
 * @author Grayson Kuhns
 */
public interface JenkinsClientFactory {

  /**
   * Creates a {@link JenkinsClient}.
   *
   * @param apiEndpoint The Jenkins API endpoint.
   * @param apiUser The {@link JenkinsApiUser}.
   * @return The {@link JenkinsClient}.
   */
  JenkinsClient create(URI apiEndpoint, JenkinsApiUser apiUser);

  /**
   * Creates a {@link JenkinsClient}.
   *
   * @param request The {@link GetInstalledPluginsRequest}.
   * @return The {@link JenkinsClient}.
   */
  JenkinsClient create(GetInstalledPluginsRequest request);
}
