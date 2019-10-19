package com.xellitix.jenkins.plugintool.cli.command;

import com.beust.jcommander.Parameters;

/**
 * {@link Command} that provides the program usage information.
 *
 * @author Garrett Ewens
 */
@Parameters(commandDescription = "Show program usage")
public class HelpCommand implements Command {

  // Constants
  private static final String NAME = "help";

  /**
   * Gets the name.
   *
   * @return The name.
   */
  @Override
  public String getName() {
    return NAME;
  }
}
