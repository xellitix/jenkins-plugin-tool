package com.xellitix.jenkins.plugintool.system;

import com.google.inject.AbstractModule;

/**
 * {@link System} module.
 *
 * @author Grayson Kuhns
 */
public class SystemModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    bind(SystemProxy.class).to(DefaultSystemProxy.class);
  }
}
