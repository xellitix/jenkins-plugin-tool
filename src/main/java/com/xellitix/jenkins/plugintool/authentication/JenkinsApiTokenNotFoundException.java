package com.xellitix.jenkins.plugintool.authentication;

/**
 * {@link JenkinsApiTokenRetriever} not found exception.
 *
 * @author Garrett Ewens
 */
public class JenkinsApiTokenNotFoundException extends RuntimeException {

  // Constants
  private static final String MSG = "A Jenkins API token was not found";

  /**
   * Constructor.
   */
  public JenkinsApiTokenNotFoundException() {
    super(MSG);
  }
}
