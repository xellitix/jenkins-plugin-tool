package com.xellitix.jenkins.plugintool.updatecenter;

import com.google.inject.AbstractModule;

/**
 * Jenkins Update Center module.
 *
 * @author Grayson Kuhns
 */
public class UpdateCenterModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    bind(UpdateCenter.class).to(DefaultUpdateCenter.class);
  }
}
