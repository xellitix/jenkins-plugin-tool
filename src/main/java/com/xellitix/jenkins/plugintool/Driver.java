package com.xellitix.jenkins.plugintool;

import com.google.inject.Guice;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterface;
import com.xellitix.jenkins.plugintool.cli.CommandLineInterfaceModule;

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
        .createInjector(new CommandLineInterfaceModule("jpt"))
        .getInstance(CommandLineInterface.class)
        .execute(args);
  }
}
