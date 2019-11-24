package com.xellitix.jenkins.plugintool.authentication;

import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;

import com.xellitix.jenkins.plugintool.system.SystemProxy;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Default {@link JenkinsApiUserRetriever} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultJenkinsApiUserRetriever implements JenkinsApiUserRetriever {

  // Constants
  private static final String ENVVAR_USERNAME = "JENKINS_API_USER";
  private static final String ENVVAR_API_TOKEN = "JENKINS_API_TOKEN";

  // Dependencies
  private final SystemProxy system;
  private final JenkinsApiUserFactory userFactory;

  /**
   * Constructor.
   *
   * @param system The {@link SystemProxy}.
   * @param userFactory The {@link JenkinsApiUserFactory}.
   */
  @Inject
  DefaultJenkinsApiUserRetriever(
      final SystemProxy system,
      final JenkinsApiUserFactory userFactory) {

    this.system = system;
    this.userFactory = userFactory;
  }

  /**
   * Gets the {@link JenkinsApiUser}.
   *
   * @param command The {@link FetchInstalledPluginsCommand}.
   * @return The {@link JenkinsApiUser}.
   * @throws JenkinsApiUserNotFoundException If a {@link JenkinsApiUser} cannot be retrieved.
   */
  @Override
  public JenkinsApiUser get(
      final FetchInstalledPluginsCommand command)
      throws JenkinsApiUserNotFoundException {

    // Attempt to get the user from the command
    final Optional<JenkinsApiUser> commandUser = command.getJenkinsApiUser();
    if (commandUser.isPresent()) {
      return commandUser.get();
    }

    // Attempt to get the user from environment variables
    final String username = system.getEnv(ENVVAR_USERNAME);
    final String apiToken = system.getEnv(ENVVAR_API_TOKEN);

    if (isValid(username) && isValid(apiToken)) {
      return userFactory.create(username, apiToken);
    }

    throw new JenkinsApiUserNotFoundException();
  }

  private boolean isValid(final String input) {
    return input != null && !input.isEmpty();
  }
}
