package com.xellitix.jenkins.plugintool.plugin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Plugin interface.
 *
 * @author Garrett Ewens
 */
@JsonPropertyOrder({ "name", "version" })
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
