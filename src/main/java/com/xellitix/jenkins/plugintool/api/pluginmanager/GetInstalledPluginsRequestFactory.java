package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.google.inject.assistedinject.Assisted;
import com.xellitix.jenkins.plugintool.api.auth.JenkinsApiUser;
import java.net.URI;

/**
 * {@link GetInstalledPluginsRequest} factory.
 *
 * @author Grayson Kuhns
 */
public interface GetInstalledPluginsRequestFactory {

  /**
   * Creates a {@link GetInstalledPluginsRequest}.
   *
   * @param apiEndpoint The Jenkins API endpoint.
   * @param apiUser The {@link JenkinsApiUser} to use for authentication.
   * @return The {@link GetInstalledPluginsRequest}.
   */
  GetInstalledPluginsRequest create(
      @Assisted URI apiEndpoint,
      @Assisted JenkinsApiUser apiUser);
}
