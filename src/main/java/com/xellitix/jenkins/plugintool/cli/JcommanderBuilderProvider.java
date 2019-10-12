package com.xellitix.jenkins.plugintool.cli;

import com.beust.jcommander.JCommander;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * {@link JCommander.Builder} {@link Provider}.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class JcommanderBuilderProvider implements Provider<JCommander.Builder> {

  /**
   * Gets a {@link JCommander.Builder}.
   *
   * @return The {@link JCommander.Builder}.
   */
  @Override
  public JCommander.Builder get() {
    return JCommander.newBuilder();
  }
}
