package com.xellitix.jenkins.plugintool.authentication;

import com.google.inject.assistedinject.Assisted;
import javax.inject.Inject;

/**
 * Default {@link JenkinsApiUser} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultJenkinsApiUser implements JenkinsApiUser {

  // Properties
  private final String username;
  private final String apiToken;

  /**
   * Constructor.
   *
   * @param username The username.
   * @param apiToken The API token.
   */
  @Inject
  DefaultJenkinsApiUser(
      @Assisted("username") final String username,
      @Assisted("apiToken") final String apiToken) {

    this.username = username;
    this.apiToken = apiToken;
  }

  /**
   * Gets the username.
   *
   * @return The username.
   */
  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Gets the API token.
   *
   * @return The API token.
   */
  @Override
  public String getApiToken() {
    return apiToken;
  }
}
