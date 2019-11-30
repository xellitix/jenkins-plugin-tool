package com.xellitix.jenkins.plugintool.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.output.PluginListOutputFormat;
import java.net.URI;
import java.util.Optional;

/**
 * {@link Command} for fetching the plugins that currently installed on a remote Jenkins instance.
 *
 * @author Grayson Kuhns
 */
@Parameters(commandDescription = "Fetch plugins installed on a Jenkins instance")
public class FetchInstalledPluginsCommand implements Command {

  // Constants
  private static final String COMMAND_NAME =
      "fetch-installed-plugins";

  private static final PluginListOutputFormat DEFAULT_OUTPUT_FORMAT =
      PluginListOutputFormat.JENKINS;

  // Properties
  @Parameter(
      names = "--jenkins-endpoint",
      description = "The Jenkins endpoint URI",
      required = true)
  private URI jenkinsEndpoint;

  @Parameter(
      names = "--jenkins-credentials",
      description = "The credentials used to authenticate to jenkins")
  private JenkinsApiUser jenkinsApiUser;

  @Parameter(
      names = "--output-format",
      description = "The output format. Valid formats: JENKINS, JSON, YAML")
  private PluginListOutputFormat outputFormat;

  @Parameter(
      names = "--latest-releases",
      description = "Get the latest release of each plugin")
  private Boolean latestReleases = false;

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
    return COMMAND_NAME;
  }

  /**
   * Gets the {@link JenkinsApiUser}.
   *
   * @return The {@link JenkinsApiUser}.
   */
  public Optional<JenkinsApiUser> getJenkinsApiUser() {
    return Optional.ofNullable(jenkinsApiUser);
  }

  /**
   * Gets the {@link PluginListOutputFormat}.
   *
   * @return The {@link PluginListOutputFormat}.
   */
  public PluginListOutputFormat getOutputFormat() {
    if (outputFormat == null) {
      return DEFAULT_OUTPUT_FORMAT;
    }

    return outputFormat;
  }

  /**
   * Determines if we should get the latest release of plugins.
   *
   * @return True if we should get the latest release of plugins.
   */
  public boolean isGetLatestReleasesEnabled() {
    return latestReleases;
  }
}
