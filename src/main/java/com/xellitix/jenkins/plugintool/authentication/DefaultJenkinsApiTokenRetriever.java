package com.xellitix.jenkins.plugintool.authentication;

import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;

import com.xellitix.jenkins.plugintool.output.PluginListOutputFormat;
import com.xellitix.jenkins.plugintool.system.SystemProxy;
import java.util.Arrays;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Default {@link JenkinsApiTokenRetriever} implementation.
 *
 * @author Garrett Ewens
 */
@Singleton
public class DefaultJenkinsApiTokenRetriever implements JenkinsApiTokenRetriever {

  // Constants
  private static final String ENV_VAR_NAME = "JENKINS_API_TOKEN";

  // Dependencies
  private final SystemProxy system;

  /**
   * Constructor.
   *
   * @param system The {@link SystemProxy}.
   */
  @Inject
  DefaultJenkinsApiTokenRetriever(final SystemProxy system) {
    this.system = system;
  }

  /**
   * Gets the API token.
   *
   * @param command The {@link FetchInstalledPluginsCommand}.
   * @return The API token.
   * @throws JenkinsApiTokenNotFoundException If an API token cannot be found.
   */
  @Override
  public String getApiToken(final FetchInstalledPluginsCommand command) {
    // Use the API token specified in the command if it is present
    final Optional<String> commandApiToken = command.getApiToken();
    if (commandApiToken.isPresent() && !commandApiToken.get().isEmpty()) {
      return commandApiToken.get();
    }

    // Use the API token specified by the environment variable if it is set
    final String envApiToken = system.getEnv(ENV_VAR_NAME);
    if (envApiToken != null && !envApiToken.isEmpty()) {
      return envApiToken;
    }

    // No valid API token was found
    throw new JenkinsApiTokenNotFoundException();
  }
}
