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
   */
  String getApiToken(FetchInstalledPluginsCommand command);
}
