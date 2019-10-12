package com.xellitix.jenkins.plugintool.cli;

/**
 * Command line interface.
 *
 * @author Grayson Kuhns
 */
public interface CommandLineInterface {

  /**
   * Executes a command.
   *
   * @param args The command arguments.
   */
  void execute(String[] args);
}
