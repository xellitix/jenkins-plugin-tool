package com.xellitix.jenkins.plugintool.authentication;

import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;

/**
 * Jenkins API token retriever interface.
 *
 * @author Garrett Ewens
 */
public interface JenkinsApiTokenRetriever {

  /**
   * Gets the API token.
   *
   * @param command The {@link FetchInstalledPluginsCommand}.
   * @return The API token.
   * @throws JenkinsApiTokenNotFoundException If an API token cannot be found.
   */
  String getApiToken(FetchInstalledPluginsCommand command) throws JenkinsApiTokenNotFoundException;
}
