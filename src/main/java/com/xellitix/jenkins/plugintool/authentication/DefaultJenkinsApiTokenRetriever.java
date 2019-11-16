package com.xellitix.jenkins.plugintool.authentication;

import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;

import javax.inject.Singleton;

/**
 * Default {@link JenkinsApiTokenRetriever} implementation.
 *
 * @author Garrett Ewens
 */
@Singleton
public class DefaultJenkinsApiTokenRetriever implements JenkinsApiTokenRetriever {

  // Constants
  private static final String environVar = System.getenv("JENKINS_API_TOKEN");

  /**
   * Gets the API token.
   *
   * @param command The {@link FetchInstalledPluginsCommand}.
   * @return The API token.
   * @throws JenkinsApiTokenNotFoundException If an API token cannot be found.
   */
  @Override
  public String getApiToken(FetchInstalledPluginsCommand command) {
    if (command.getApiToken().isPresent()) {
      return command.getApiToken().get();
    } else if (!environVar.isEmpty()) {
      return environVar;
    } else {
      throw new JenkinsApiTokenNotFoundException();
    }
  }
}
