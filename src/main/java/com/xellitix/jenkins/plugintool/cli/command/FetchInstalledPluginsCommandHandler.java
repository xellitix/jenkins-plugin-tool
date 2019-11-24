package com.xellitix.jenkins.plugintool.cli.command;

import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequest;
import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequestExecutor;
import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequestFactory;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUserRetriever;
import com.xellitix.jenkins.plugintool.output.PluginListOutputWriter;
import com.xellitix.jenkins.plugintool.output.PluginListOutputWriterLocator;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import java.util.List;
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
  private final GetInstalledPluginsRequestFactory requestFactory;
  private final GetInstalledPluginsRequestExecutor requestExecutor;
  private final PluginListOutputWriterLocator outputWriterLocator;

  /**
   * Constructor.
   *
   * @param apiUserRetriever The {@link JenkinsApiUserRetriever}.
   * @param requestFactory The {@link GetInstalledPluginsRequestFactory}.
   * @param requestExecutor The {@link GetInstalledPluginsRequestExecutor}.
   * @param outputWriterLocator The {@link PluginListOutputWriterLocator}.
   */
  @Inject
  FetchInstalledPluginsCommandHandler(
      final JenkinsApiUserRetriever apiUserRetriever,
      final GetInstalledPluginsRequestFactory requestFactory,
      final GetInstalledPluginsRequestExecutor requestExecutor,
      final PluginListOutputWriterLocator outputWriterLocator) {

    this.apiUserRetriever = apiUserRetriever;
    this.requestFactory = requestFactory;
    this.requestExecutor = requestExecutor;
    this.outputWriterLocator = outputWriterLocator;
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

    // Create the action request
    final GetInstalledPluginsRequest request =
        requestFactory.create(command.getJenkinsEndpoint(), apiUser);

    // Get the installed plugins
    final List<Plugin> plugins = requestExecutor.execute(request);

    // Get the output writer
    final PluginListOutputWriter outputWriter =
        outputWriterLocator.locate(command.getOutputFormat());

    // Write the output
    outputWriter.write(plugins, System.out);
  }
}
