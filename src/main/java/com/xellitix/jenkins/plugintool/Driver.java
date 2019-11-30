package com.xellitix.jenkins.plugintool;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.xellitix.commons.net.compat.java.JavaNetCompatibilityModule;
import com.xellitix.jenkins.plugintool.api.pluginmanager.PluginManagerApiModule;
import com.xellitix.jenkins.plugintool.authentication.JenkinsAuthenticationModule;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterface;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterfaceModule;
import com.xellitix.jenkins.plugintool.http.HttpModule;
import com.xellitix.jenkins.plugintool.output.OutputModule;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import com.xellitix.jenkins.plugintool.plugin.PluginModule;
import com.xellitix.jenkins.plugintool.system.SystemModule;
import com.xellitix.jenkins.plugintool.updatecenter.UpdateCenter;
import com.xellitix.jenkins.plugintool.updatecenter.UpdateCenterModule;

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
    final Injector injector = Guice
        .createInjector(
            new CommandLineInterfaceModule("jpt"),
            new PluginModule(),
            new JenkinsAuthenticationModule(),
            new PluginManagerApiModule(),
            new UpdateCenterModule(),
            new OutputModule(),
            new HttpModule(),
            new SystemModule(),
            new JavaNetCompatibilityModule());

    // Run the application
    injector
        .getInstance(CommandLineInterface.class)
        .execute(args);
  }
}
