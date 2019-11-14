package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link GetInstalledPluginsRequestExecutor} implemented using Cdancy's Jenkins REST API client.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class CdancyGetInstalledPluginsRequestExecutor
    implements GetInstalledPluginsRequestExecutor {

  // Constants
  private static final int PLUGIN_DEPTH = 5;

  // Dependencies
  private final JenkinsClientFactory clientFactory;
  private final PluginConverter pluginConverter;

  /**
   * Constructor.
   *
   * @param clientFactory The {@link JenkinsClientFactory}.
   * @param pluginConverter The {@link PluginConverter}.
   */
  @Inject
  CdancyGetInstalledPluginsRequestExecutor(
      final JenkinsClientFactory clientFactory,
      final PluginConverter pluginConverter) {

    this.clientFactory = clientFactory;
    this.pluginConverter = pluginConverter;
  }

  /**
   * Executes a {@link GetInstalledPluginsRequest}.
   *
   * @param request The {@link GetInstalledPluginsRequest}.
   * @return The {@link Plugin}s discovered.
   */
  @Override
  public List<Plugin> execute(final GetInstalledPluginsRequest request) {
    return clientFactory
        .create(request)
        .api()
        .pluginManagerApi()
        .plugins(PLUGIN_DEPTH, null)
        .plugins()
        .stream()
        .map(pluginConverter::convert)
        .collect(Collectors.toList());
  }
}
