package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import java.net.URI;

/**
 * Request to get installed plugins on a remote Jenkins instance.
 *
 * @author Grayson Kuhns
 */
public interface GetInstalledPluginsRequest {

  /**
   * Gets the Jenkins API endpoint.
   *
   * @return The Jenkins API endpoint.
   */
  URI getApiEndpoint();

  /**
   * Gets the {@link JenkinsApiUser} to use for authentication.
   *
   * @return The {@link JenkinsApiUser} to use for authentication.
   */
  JenkinsApiUser getApiUser();
}
