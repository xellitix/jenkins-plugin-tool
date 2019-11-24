package com.xellitix.jenkins.plugintool.authentication;

/**
 * Jenkins API user credentials.
 *
 * @author Grayson Kuhns
 */
public interface JenkinsApiUser {

  /**
   * Gets the username.
   *
   * @return The username.
   */
  String getUsername();

  /**
   * Gets the API token.
   *
   * @return The API token.
   */
  String getApiToken();
}
