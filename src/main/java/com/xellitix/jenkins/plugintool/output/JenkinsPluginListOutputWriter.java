package com.xellitix.jenkins.plugintool.output;

import com.xellitix.jenkins.plugintool.plugin.Plugin;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * JENKINS {@link PluginListOutputWriter} implementation.
 *
 * @author Garrett Ewens
 */
public class JenkinsPluginListOutputWriter implements PluginListOutputWriter {
  /**
   * Gets the {@link PluginListOutputFormat}.
   *
   * @return The {@link PluginListOutputFormat}.
   */
  @Override
  public PluginListOutputFormat getFormat() {
    return PluginListOutputFormat.JENKINS;
  }

  /**
   * Writes a {@link Plugin} {@link List}.
   *
   * @param plugins The {@link Plugin}s.
   * @param output  The {@link OutputStream}.
   */
  @Override
  public void write(final List<Plugin> plugins, final PrintStream output) {
    plugins.forEach((value) -> {
      output.println(value.getName() + ":" + value.getVersion());
    });
  }
}
