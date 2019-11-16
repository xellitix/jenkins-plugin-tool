package com.xellitix.jenkins.plugintool.api.auth;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link JenkinsApiUser} factory.
 *
 * @author Grayson Kuhns
 */
public interface JenkinsApiUserFactory {

  /**
   * Creates a {@link JenkinsApiUser}.
   *
   * @param username The username.
   * @param apiToken The API token.
   * @return The {@link JenkinsApiUser}.
   */
  JenkinsApiUser create(
      @Assisted("username") String username,
      @Assisted("apiToken") String apiToken);
}
