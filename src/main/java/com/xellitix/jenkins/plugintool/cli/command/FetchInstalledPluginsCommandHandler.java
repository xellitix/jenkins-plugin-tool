package com.xellitix.jenkins.plugintool.cli.command;

/**
 * {@link FetchInstalledPluginsCommand} {@link CommandHandler}.
 *
 * @author Grayson Kuhns
 */
public class FetchInstalledPluginsCommandHandler
    implements CommandHandler<FetchInstalledPluginsCommand> {

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
    System.out.printf(
        "Fetch installed plugins on remote Jenkins instance %s",
        command.getJenkinsEndpoint().toASCIIString());
  }
}
