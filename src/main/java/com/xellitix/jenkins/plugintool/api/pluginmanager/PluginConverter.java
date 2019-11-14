package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.xellitix.jenkins.plugintool.plugin.Plugin;

/**
 * {@link Plugin} converter.
 *
 * @author Grayson Kuhns
 */
public interface PluginConverter {

  /**
   * Converts a {@link com.cdancy.jenkins.rest.domain.plugins.Plugin}
   *     to a {@link com.xellitix.jenkins.plugintool.plugin.Plugin}.
   *
   * @param plugin The {@link com.cdancy.jenkins.rest.domain.plugins.Plugin} to convert.
   * @return The converted {@link com.xellitix.jenkins.plugintool.plugin.Plugin}.
   */
  Plugin convert(com.cdancy.jenkins.rest.domain.plugins.Plugin plugin);
}
