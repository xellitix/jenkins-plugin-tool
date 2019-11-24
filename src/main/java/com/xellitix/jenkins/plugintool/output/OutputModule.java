package com.xellitix.jenkins.plugintool.output;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * Output module.
 *
 * @author Grayson Kuhns
 */
public class OutputModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // Plugin list output writers
    final Multibinder<PluginListOutputWriter> pluginListOutputWriterMultibinder =
        Multibinder.newSetBinder(binder(), PluginListOutputWriter.class);

    pluginListOutputWriterMultibinder
        .addBinding()
        .to(JenkinsPluginListOutputWriter.class);

    pluginListOutputWriterMultibinder
        .addBinding()
        .to(JsonPluginListOutputWriter.class);

    pluginListOutputWriterMultibinder
        .addBinding()
        .to(YamlPluginListOutputWriter.class);

    // Plugin list output writer locator
    bind(PluginListOutputWriterLocator.class)
        .to(DefaultPluginListOutputWriterLocator.class);
  }
}
