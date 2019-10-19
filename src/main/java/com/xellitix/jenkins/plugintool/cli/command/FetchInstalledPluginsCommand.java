package com.xellitix.jenkins.plugintool.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.net.URI;

/**
 * {@link Command} for fetching the plugins that currently installed on a remote Jenkins instance.
 *
 * @author Grayson Kuhns
 */
@Parameters(commandDescription = "Fetch plugins installed on a Jenkins instance")
public class FetchInstalledPluginsCommand implements Command {

  // Constants
  private static final String NAME = "fetch-installed-plugins";

  // Properties
  @Parameter(
      names = "--jenkins-endpoint",
      description = "The Jenkins endpoint URI",
      required = true)
  private URI jenkinsEndpoint;

  /**
   * Gets the Jenkins endpoint.
   *
   * @return The Jenkins endpoint.
   */
  public URI getJenkinsEndpoint() {
    return jenkinsEndpoint;
  }

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
