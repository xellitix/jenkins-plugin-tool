package com.xellitix.jenkins.plugintool;

import com.google.inject.Guice;
import com.xellitix.jenkins.plugintool.api.pluginmanager.PluginManagerApiModule;
import com.xellitix.jenkins.plugintool.authentication.JenkinsAuthenticationModule;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterface;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterfaceModule;
import com.xellitix.jenkins.plugintool.output.OutputModule;
import com.xellitix.jenkins.plugintool.plugin.PluginModule;
import com.xellitix.jenkins.plugintool.system.SystemModule;

/**
 * The program entry point.
 *
 * @author Grayson Kuhns
 */
public class Driver {

  /**
   * The program entry point.
   *
   * @param args The command line arguments.
   */
  public static void main(final String[] args) {
    // Execute the command
    Guice
        .createInjector(
            new CommandLineInterfaceModule("jpt"),
            new PluginModule(),
            new JenkinsAuthenticationModule(),
            new PluginManagerApiModule(),
            new OutputModule(),
            new SystemModule())
        .getInstance(CommandLineInterface.class)
        .execute(args);
  }
}
