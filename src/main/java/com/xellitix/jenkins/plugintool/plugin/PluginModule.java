package com.xellitix.jenkins.plugintool.plugin;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Plugin module.
 *
 * @author Garrett Ewens
 */
public class PluginModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // Plugin factory
    install(new FactoryModuleBuilder()
      .implement(Plugin.class, DefaultPlugin.class)
      .build(PluginFactory.class));
  }
}
