package com.xellitix.jenkins.plugintool.authentication;

/**
 * {@link JenkinsApiUserRetriever} not found exception.
 *
 * @author Garrett Ewens
 */
public class JenkinsApiUserNotFoundException extends RuntimeException {

  // Constants
  private static final String MSG = "A Jenkins API user was not found";

  /**
   * Constructor.
   */
  public JenkinsApiUserNotFoundException() {
    super(MSG);
  }
}
