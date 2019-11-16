package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.xellitix.jenkins.plugintool.plugin.Plugin;
import java.util.List;

/**
 * {@link GetInstalledPluginsRequest} executor.
 *
 * @author Grayson Kuhns
 */
public interface GetInstalledPluginsRequestExecutor {

  /**
   * Executes a {@link GetInstalledPluginsRequest}.
   *
   * @param request The {@link GetInstalledPluginsRequest}.
   * @return The {@link Plugin}s discovered.
   */
  List<Plugin> execute(GetInstalledPluginsRequest request);
}
