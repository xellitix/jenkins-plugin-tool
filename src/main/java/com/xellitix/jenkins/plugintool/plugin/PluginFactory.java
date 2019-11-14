package com.xellitix.jenkins.plugintool.plugin;

import com.google.inject.assistedinject.Assisted;

/**
 * Plugin factory interface.
 *
 * @author Garrett Ewens
 */
public interface PluginFactory {

  /**
   * Creates a {@link Plugin}.
   *
   * @param name
   * @param version
   * @return {@link Plugin}
   */
  Plugin create(
      @Assisted("name") String name,
      @Assisted("version") String version);
}
