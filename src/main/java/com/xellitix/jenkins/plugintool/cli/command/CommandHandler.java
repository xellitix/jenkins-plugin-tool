package com.xellitix.jenkins.plugintool.cli.command;

/**
 * {@link Command} handler.
 *
 * @param <T> The {@link Command} type.
 */
public interface CommandHandler<T extends Command> {

  /**
   * Gets the {@link Command} type handled.
   *
   * @return The {@link Command} type handled.
   */
  Class<T> getHandledType();

  /**
   * Handles a {@link Command}.
   *
   * @param command The {@link Command}.
   */
  void handle(T command);
}
