package com.xellitix.jenkins.plugintool.cli;

import com.beust.jcommander.JCommander;
import com.xellitix.jenkins.plugintool.cli.command.Command;
import com.xellitix.jenkins.plugintool.cli.command.CommandHandler;
import com.xellitix.jenkins.plugintool.cli.command.CommandHandlerNotFoundException;
import com.xellitix.jenkins.plugintool.cli.converter.ParameterConverterLocator;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link JCommander} {@link CommandLineInterface} implementation.
 *
 * @author Grayson Kuhns
 */
public class JcommanderInterface implements CommandLineInterface {

  // Properties
  private final Set<Command> commands;
  private final Set<CommandHandler<? extends Command>> commandHandlers;
  private final JCommander jcommander;

  /**
   * Constructor.
   *
   * @param programName The program name.
   * @param commands The {@link Command}s.
   * @param commandHandlers The {@link CommandHandler}s.
   * @param jcommanderBuilder The {@link JCommander.Builder}.
   * @param parameterConverterLocator The {@link ParameterConverterLocator}.
   */
  @Inject
  JcommanderInterface(
      @Named("programName") final String programName,
      final Set<Command> commands,
      final Set<CommandHandler<? extends Command>> commandHandlers,
      final JCommander.Builder jcommanderBuilder,
      final ParameterConverterLocator parameterConverterLocator) {

    this.commands = commands;
    this.commandHandlers = commandHandlers;

    // Register the program name
    jcommanderBuilder.programName(programName);

    // Register the parameter converters
    jcommanderBuilder.addConverterInstanceFactory(parameterConverterLocator);

    // Register the commands
    commands.forEach(command ->
        jcommanderBuilder.addCommand(command.getName(), command));

    // Build JCommander
    jcommander = jcommanderBuilder.build();
  }

  /**
   * Executes a command.
   *
   * @param args The command arguments.
   */
  @Override
  public void execute(final String[] args) {
    // Parse the arguments
    jcommander.parse(args);

    // Get the name of the command specified by the input arguments
    final String specifiedCommand = jcommander.getParsedCommand();

    // Get the command
    final Optional<Command> command = getCommand(specifiedCommand);

    // Show usage information if no command was specified
    if (!command.isPresent() || command.get().getName().equals("help")) {
      jcommander.usage();
      return;
    }

    // Get the command handler
    final CommandHandler commandHandler = getCommandHandler(command.get());

    // Handle the command
    commandHandler.handle(command.get());
  }

  private Optional<Command> getCommand(final String commandName) {
    if (commandName == null) {
      return Optional.empty();
    }

    return commands
        .stream()
        .filter(command -> command
            .getName()
            .equals(commandName))
        .findFirst();
  }

  private CommandHandler<? extends Command> getCommandHandler(final Command command) {
    return commandHandlers
        .stream()
        .filter(handler -> handler
            .getHandledType()
            .isInstance(command))
        .findFirst()
        .orElseThrow(() -> new CommandHandlerNotFoundException(command.getClass()));
  }
}
