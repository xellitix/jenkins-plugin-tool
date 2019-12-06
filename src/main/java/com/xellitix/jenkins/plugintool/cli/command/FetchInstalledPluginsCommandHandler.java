package com.xellitix.jenkins.plugintool.cli.command;

import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequest;
import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequestExecutor;
import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequestFactory;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUserRetriever;
import com.xellitix.jenkins.plugintool.output.PluginListOutputWriter;
import com.xellitix.jenkins.plugintool.output.PluginListOutputWriterLocator;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import com.xellitix.jenkins.plugintool.updatecenter.UpdateCenter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
  private final UpdateCenter updateCenter;

  /**
   * Constructor.
   *
   * @param apiUserRetriever The {@link JenkinsApiUserRetriever}.
   * @param requestFactory The {@link GetInstalledPluginsRequestFactory}.
   * @param requestExecutor The {@link GetInstalledPluginsRequestExecutor}.
   * @param outputWriterLocator The {@link PluginListOutputWriterLocator}.
   * @param updateCenter The {@link UpdateCenter}.
   */
  @Inject
  FetchInstalledPluginsCommandHandler(
      final JenkinsApiUserRetriever apiUserRetriever,
      final GetInstalledPluginsRequestFactory requestFactory,
      final GetInstalledPluginsRequestExecutor requestExecutor,
      final PluginListOutputWriterLocator outputWriterLocator,
      final UpdateCenter updateCenter) {

    this.apiUserRetriever = apiUserRetriever;
    this.requestFactory = requestFactory;
    this.requestExecutor = requestExecutor;
    this.outputWriterLocator = outputWriterLocator;
    this.updateCenter = updateCenter;
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
    Stream<Plugin> pluginStream = requestExecutor
        .execute(request)
        .stream();

    // Update the plugins if requested
    if (command.isGetLatestReleasesEnabled()) {
      pluginStream = pluginStream
          .map(updateCenter::getLatestRelease)
          .filter(Optional::isPresent)
          .map(Optional::get);
    }

    // Sort the plugins lexicographically by name
    final List<Plugin> plugins = pluginStream
        .sorted()
        .collect(Collectors.toList());

    // Get the output writer
    final PluginListOutputWriter outputWriter =
        outputWriterLocator.locate(command.getOutputFormat());

    // Write the output
    outputWriter.write(plugins, System.out);
  }
}
