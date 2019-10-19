package com.xellitix.jenkins.plugintool.cli.command;

/**
 * {@link CommandHandler} not found exception.
 *
 * @author Grayson Kuhns
 */
public class CommandHandlerNotFoundException extends RuntimeException {

  // Constants
  private static final String MSG_TEMPLATE =
      "A CommandHandler was not found for Command type: %s";

  /**
   * Constructor.
   *
   * @param commandType The {@link Command} type for which a
   *     {@link CommandHandler} could not be found.
   */
  public CommandHandlerNotFoundException(final Class<? extends Command> commandType) {
    super(String.format(MSG_TEMPLATE, commandType.getName()));
  }
}
