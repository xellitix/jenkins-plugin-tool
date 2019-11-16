package com.xellitix.jenkins.plugintool.authentication;

import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;

/**
 * Default {@link JenkinsApiTokenRetriever} implementation.
 *
 * @author Garrett Ewens
 */
public class DefaultJenkinsApiTokenRetriever implements JenkinsApiTokenRetriever {

  /**
   * Gets the API token.
   *
   * @return The API token.
   */
  @Override
  public String getApiToken(FetchInstalledPluginsCommand command) {
    if (command.getApiToken().isPresent()) {
      return command.getApiToken().get();
    } else if (!System.getenv("JENKINS_API_TOKEN").isEmpty()
        && System.getenv("JENKINS_API_TOKEN") != null) {
      return System.getenv("JENKINS_API_TOKEN");
    } else {
      throw new JenkinsApiTokenNotFoundException();
    }
  }
}
