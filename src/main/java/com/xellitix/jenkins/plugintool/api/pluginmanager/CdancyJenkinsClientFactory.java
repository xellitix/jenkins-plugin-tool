package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.xellitix.jenkins.plugintool.api.auth.JenkinsApiUser;
import java.net.URI;

/**
 * Cdancy {@link JenkinsClientFactory} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class CdancyJenkinsClientFactory implements JenkinsClientFactory {

  // Dependencies
  private final Provider<JenkinsClient.Builder> builderProvider;

  /**
   * Constructor.
   *
   * @param builderProvider The {@link JenkinsClient.Builder} {@link Provider}.
   */
  @Inject
  CdancyJenkinsClientFactory(final Provider<JenkinsClient.Builder> builderProvider) {
    this.builderProvider = builderProvider;
  }

  /**
   * Creates a {@link JenkinsClient}.
   *
   * @param apiEndpoint The Jenkins API endpoint.
   * @param apiUser The {@link JenkinsApiUser}.
   * @return The {@link JenkinsClient}.
   */
  @Override
  public JenkinsClient create(
      final URI apiEndpoint,
      final JenkinsApiUser apiUser) {

    // Assemble the authentication string
    final String authString = String.format(
        "%s:%s",
        apiUser.getUsername(),
        apiUser.getApiToken());

    // Create the JenkinsClient
    return builderProvider
        .get()
        .endPoint(apiEndpoint.toASCIIString())
        .credentials(authString)
        .build();
  }

  /**
   * Creates a {@link JenkinsClient}.
   *
   * @param request The {@link GetInstalledPluginsRequest}.
   * @return The {@link JenkinsClient}.
   */
  @Override
  public JenkinsClient create(final GetInstalledPluginsRequest request) {
    return create(
        request.getApiEndpoint(),
        request.getApiUser());
  }
}
