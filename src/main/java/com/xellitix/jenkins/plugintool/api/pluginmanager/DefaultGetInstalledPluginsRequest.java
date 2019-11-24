package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.google.inject.assistedinject.Assisted;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import java.net.URI;
import javax.inject.Inject;

/**
 * Default {@link GetInstalledPluginsRequest} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultGetInstalledPluginsRequest implements GetInstalledPluginsRequest {

  // Properties
  private final URI apiEndpoint;
  private final JenkinsApiUser apiUser;

  /**
   * Constructor.
   *
   * @param apiEndpoint The Jenkins API endpoint.
   * @param apiUser The {@link JenkinsApiUser} to use for authentication.
   */
  @Inject
  public DefaultGetInstalledPluginsRequest(
      @Assisted final URI apiEndpoint,
      @Assisted final JenkinsApiUser apiUser) {

    this.apiEndpoint = apiEndpoint;
    this.apiUser = apiUser;
  }

  /**
   * Gets the Jenkins API endpoint.
   *
   * @return The Jenkins API endpoint.
   */
  @Override
  public URI getApiEndpoint() {
    return apiEndpoint;
  }

  /**
   * Gets the {@link JenkinsApiUser} to use for authentication.
   *
   * @return The {@link JenkinsApiUser} to use for authentication.
   */
  @Override
  public JenkinsApiUser getApiUser() {
    return apiUser;
  }
}
