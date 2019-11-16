package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.xellitix.jenkins.plugintool.plugin.Plugin;
import com.xellitix.jenkins.plugintool.plugin.PluginFactory;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Default {@link PluginConverter} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultPluginConverter implements PluginConverter {

  // Dependencies
  private final PluginFactory pluginFactory;

  /**
   * Constructor.
   *
   * @param pluginFactory The {@link PluginFactory}.
   */
  @Inject
  DefaultPluginConverter(final PluginFactory pluginFactory) {
    this.pluginFactory = pluginFactory;
  }

  /**
   * Converts a {@link com.cdancy.jenkins.rest.domain.plugins.Plugin}
   * to a {@link Plugin}.
   *
   * @param plugin The {@link com.cdancy.jenkins.rest.domain.plugins.Plugin} to convert.
   * @return The converted {@link Plugin}.
   */
  @Override
  public Plugin convert(final com.cdancy.jenkins.rest.domain.plugins.Plugin plugin) {
    // The factory will perform non-null checks
    return pluginFactory.create(
        plugin.shortName(),
        plugin.version());
  }
}
