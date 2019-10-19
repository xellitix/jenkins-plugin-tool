package com.xellitix.jenkins.plugintool.cli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.beust.jcommander.JCommander;
import com.google.common.collect.ImmutableSet;
import com.xellitix.jenkins.plugintool.cli.command.Command;
import com.xellitix.jenkins.plugintool.cli.command.CommandHandler;
import com.xellitix.jenkins.plugintool.cli.command.CommandHandlerNotFoundException;
import com.xellitix.jenkins.plugintool.cli.converter.ParameterConverterLocator;
import java.util.Set;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link JcommanderInterface} test case.
 *
 * @author Grayson Kuhns
 */
public class JcommanderInterfaceTest {

  // Constants
  private static final String PROGRAM_NAME = "jpt";

  private static final String COMMAND_ONE_NAME = "commandOne";
  private static final String COMMAND_TWO_NAME = "commandTwo";
  private static final String COMMAND_HELP_NAME = "help";

  private static final String MSG_HANDLER_NOT_FOUND =
      "A CommandHandler was not found for Command type: " +
          "com.xellitix.jenkins.plugintool.cli.JcommanderInterfaceTest$CommandTwo";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private JCommander jcommander;
  private JCommander.Builder jcommanderBuilder;

  private CommandOne commandOne;
  private CommandTwo commandTwo;
  private CommandOneHandler commandOneHandler;

  private ParameterConverterLocator parameterConverterLocator;
  private JcommanderInterface jcommanderInterface;

  @Test
  public void programNameIsSet__Test() {
    verify(jcommanderBuilder).programName(eq(PROGRAM_NAME));
  }

  @Test
  public void parameterConverterLocatorIsRegistered__Test() {
    verify(jcommanderBuilder).addConverterInstanceFactory(parameterConverterLocator);
  }

  @Test
  public void commandsAreRegistered__Test() {
    verify(jcommanderBuilder).addCommand(eq(COMMAND_ONE_NAME), eq(commandOne));
    verify(jcommanderBuilder).addCommand(eq(COMMAND_TWO_NAME), eq(commandTwo));
  }

  @Test
  public void execute__ParsesArgumentsUsingJcommander__Test() {
    // Define the arguments
    final String[] args = { "help", "--test" };

    // Execute the arguments
    jcommanderInterface.execute(args);

    // Verify that the arguments were passed to JCommander
    verify(jcommander).parse(eq("help"), eq("--test"));
  }

  @Test
  public void execute__DisplaysUsageInformation__WhenCommandIsNotSpecified__Test() {
    // Do not specify a command
    doReturn(null)
        .when(jcommander)
        .getParsedCommand();

    // Execute without any arguments
    jcommanderInterface.execute(new String[0]);

    // Verify that usage information is displayed
    verify(jcommander).usage();
  }

  @Test
  public void execute__DisplaysUsageInformation__WhenHelpCommandIsSpecified__Test() {
    // Specify help command
    doReturn(COMMAND_HELP_NAME)
        .when(jcommander)
        .getParsedCommand();

    // Execute help command
    jcommanderInterface.execute(new String[] { COMMAND_HELP_NAME });

    // Verify that usage information is displayed
    verify(jcommander).usage();
  }

  @Test
  public void execute__DoesNotDisplayUsageInformation__WhenValidCommandIsSpecified__Test() {
    // Specify command one
    doReturn(COMMAND_ONE_NAME)
        .when(jcommander)
        .getParsedCommand();

    // Execute command one
    jcommanderInterface.execute(new String[] { COMMAND_ONE_NAME });

    // Verify that usage information is not displayed
    verify(jcommander, never()).usage();
  }

  @Test
  public void execute__CallsTheHandlerForValidCommands__Test() {
    // Specify command one
    doReturn(COMMAND_ONE_NAME)
        .when(jcommander)
        .getParsedCommand();

    // Execute command one
    jcommanderInterface.execute(new String[] { COMMAND_ONE_NAME });

    // Verify that the command handler was called
    verify(commandOneHandler).handle(eq(commandOne));
  }

  @Test
  public void execute__ThrowsException__WhenHandlerIsNotRegisteredForCommand__Test() {
    // Describe the exception to expect
    thrown.expect(CommandHandlerNotFoundException.class);
    thrown.expectMessage(MSG_HANDLER_NOT_FOUND);

    // Specify command two
    doReturn(COMMAND_TWO_NAME)
        .when(jcommander)
        .getParsedCommand();

    // Execute command two
    jcommanderInterface.execute(new String[] { COMMAND_TWO_NAME });
  }

  @Before
  public void setUp() {
    // JCommander mocking
    jcommander = mock(JCommander.class);
    jcommanderBuilder = mock(JCommander.Builder.class);
    doReturn(jcommander)
        .when(jcommanderBuilder)
        .build();

    // Create commands
    commandOne = new CommandOne();
    commandTwo = new CommandTwo();

    Set<Command> commands = ImmutableSet.of(commandOne, commandTwo);

    // Command handler mocking
    commandOneHandler = mock(CommandOneHandler.class);
    doReturn(CommandOne.class)
        .when(commandOneHandler)
        .getHandledType();

    Set<CommandHandler<? extends Command>> commandHandlers = ImmutableSet.of(commandOneHandler);

    // Parameter converter locator mocking
    parameterConverterLocator = mock(ParameterConverterLocator.class);

    // Create the JCommander interface
    jcommanderInterface = new JcommanderInterface(
        PROGRAM_NAME, commands, commandHandlers, jcommanderBuilder, parameterConverterLocator);
  }

  // Test types
  private static class CommandOne implements Command {

    /**
     * Gets the name.
     *
     * @return The name.
     */
    @Override
    public String getName() {
      return COMMAND_ONE_NAME;
    }
  }

  private static class CommandTwo implements Command {

    /**
     * Gets the name.
     *
     * @return The name.
     */
    @Override
    public String getName() {
      return COMMAND_TWO_NAME;
    }
  }

  private interface CommandOneHandler extends CommandHandler<CommandOne> {
  }
}
