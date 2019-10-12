package com.xellitix.jenkins.plugintool;

import com.google.inject.Guice;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterface;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterfaceModule;

/**
 * The program entrypoint.
 *
 * @author Grayson Kuhns
 */
public class Driver {

  /**
   * The program entrypoint.
   *
   * @param args The command line arguments.
   */
  public static void main(final String[] args) {
    // Execute the command
    Guice
        .createInjector(new CommandLineInterfaceModule("jpm"))
        .getInstance(CommandLineInterface.class)
        .execute(args);
  }
}
