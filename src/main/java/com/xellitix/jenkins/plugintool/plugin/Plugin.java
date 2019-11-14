package com.xellitix.jenkins.plugintool.plugin;

/**
 * Plugin interface.
 *
 * @author Garrett Ewens
 */
public interface Plugin {

  /**
   * Gets the plugin name.
   *
   * @return name.
   */
  String getName();

  /**
   * Gets the plugin version.
   *
   * @return version.
   */
  String getVersion();
}
