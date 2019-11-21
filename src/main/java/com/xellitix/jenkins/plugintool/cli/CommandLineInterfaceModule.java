package com.xellitix.jenkins.plugintool.cli;

import com.beust.jcommander.JCommander;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.xellitix.jenkins.plugintool.cli.command.Command;
import com.xellitix.jenkins.plugintool.cli.command.CommandHandler;
import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;
import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommandHandler;
import com.xellitix.jenkins.plugintool.cli.command.HelpCommand;
import com.xellitix.jenkins.plugintool.cli.converter.ParameterConverter;
import com.xellitix.jenkins.plugintool.cli.converter.PluginListOutputFormatConverter;
import com.xellitix.jenkins.plugintool.cli.converter.UriConverter;
import com.xellitix.jenkins.plugintool.output.PluginListOutputFormat;

/**
 * Command line module.
 *
 * @author Grayson Kuhns
 */
public class CommandLineInterfaceModule extends AbstractModule {

  // Properties
  private final String programName;

  /**
   * Constructor.
   *
   * @param programName The program name.
   */
  public CommandLineInterfaceModule(final String programName) {
    this.programName = programName;
  }

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // Program name
    bind(String.class)
        .annotatedWith(Names.named("programName"))
        .toInstance(programName);

    // JCommander builder
    bind(JCommander.Builder.class).toProvider(JcommanderBuilderProvider.class);

    // Converters
    Multibinder<ParameterConverter> converterMultibinder
        = Multibinder.newSetBinder(binder(), ParameterConverter.class);
    converterMultibinder.addBinding().to(UriConverter.class);
    converterMultibinder.addBinding().to(PluginListOutputFormatConverter.class);

    // Commands
    Multibinder<Command> commandMultibinder
        = Multibinder.newSetBinder(binder(), Command.class);
    commandMultibinder.addBinding().to(HelpCommand.class);
    commandMultibinder.addBinding().to(FetchInstalledPluginsCommand.class);

    // Command handlers
    TypeLiteral<CommandHandler<? extends Command>> commandHandlerType =
        new TypeLiteral<CommandHandler<? extends Command>>() {
        };

    Multibinder<CommandHandler<? extends Command>> commandHandlerMultibinder
        = Multibinder.newSetBinder(binder(), commandHandlerType);
    commandHandlerMultibinder.addBinding().to(FetchInstalledPluginsCommandHandler.class);

    // Command line interface
    bind(CommandLineInterface.class).to(JcommanderInterface.class);
  }
}
