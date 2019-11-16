package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Plugin manager API module.
 *
 * @author Grayson Kuhns
 */
public class PluginManagerApiModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // JenkinsClient builder
    bind(JenkinsClientBuilderProvider.class)
        .to(CdancyJenkinsClientBuilderProvider.class);

    // JenkinsClient factory
    bind(JenkinsClientFactory.class)
        .to(CdancyJenkinsClientFactory.class);

    // Plugin converter
    bind(PluginConverter.class).to(DefaultPluginConverter.class);

    // GetInstalledPluginsRequest factory
    install(new FactoryModuleBuilder()
        .implement(
            GetInstalledPluginsRequest.class,
            DefaultGetInstalledPluginsRequest.class)
        .build(GetInstalledPluginsRequestFactory.class));

    // GetInstalledPluginsRequest executor
    bind(GetInstalledPluginsRequestExecutor.class)
        .to(CdancyGetInstalledPluginsRequestExecutor.class);
  }
}
