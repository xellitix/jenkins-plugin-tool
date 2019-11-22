package com.xellitix.jenkins.plugintool.cli.command;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUserRetriever;
import javax.inject.Inject;

/**
 * {@link FetchInstalledPluginsCommand} {@link CommandHandler}.
 *
 * @author Grayson Kuhns
 */
public class FetchInstalledPluginsCommandHandler
    implements CommandHandler<FetchInstalledPluginsCommand> {

  // Dependencies
  private final JenkinsApiUserRetriever apiUserRetriever;

  /**
   * Constructor.
   *
   * @param apiUserRetriever The {@link JenkinsApiUserRetriever}.
   */
  @Inject
  FetchInstalledPluginsCommandHandler(final JenkinsApiUserRetriever apiUserRetriever) {
    this.apiUserRetriever = apiUserRetriever;
  }

  /**
   * Gets the {@link Command} type handled.
   *
   * @return The {@link Command} type handled.
   */
  @Override
  public Class<FetchInstalledPluginsCommand> getHandledType() {
    return FetchInstalledPluginsCommand.class;
  }

  /**
   * Handles a {@link Command}.
   *
   * @param command The {@link Command}.
   */
  @Override
  public void handle(final FetchInstalledPluginsCommand command) {
    // Get the API authentication credentials
    final JenkinsApiUser apiUser = apiUserRetriever.get(command);

    System.out.printf(
        "Fetch installed plugins on remote Jenkins instance %s. Authenticate as %s using %s",
        command.getJenkinsEndpoint().toASCIIString(),
        apiUser.getUsername(),
        apiUser.getApiToken());
  }
}
