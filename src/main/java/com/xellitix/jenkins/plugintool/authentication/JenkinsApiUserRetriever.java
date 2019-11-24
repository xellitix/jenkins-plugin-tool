package com.xellitix.jenkins.plugintool.authentication;

import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;

/**
 * {@link JenkinsApiUser} retriever.
 *
 * @author Grayson Kuhns
 */
public interface JenkinsApiUserRetriever {

  /**
   * Gets the {@link JenkinsApiUser}.
   *
   * @param command The {@link FetchInstalledPluginsCommand}.
   * @return The {@link JenkinsApiUser}.
   * @throws JenkinsApiUserNotFoundException If a {@link JenkinsApiUser} cannot be retrieved.
   */
  JenkinsApiUser get(FetchInstalledPluginsCommand command) throws JenkinsApiUserNotFoundException;
}
