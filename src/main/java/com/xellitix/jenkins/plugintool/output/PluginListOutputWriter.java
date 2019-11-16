package com.xellitix.jenkins.plugintool.output;

import com.xellitix.jenkins.plugintool.plugin.Plugin;
import java.io.PrintStream;
import java.util.List;

/**
 * {@link Plugin} {@link List} writer.
 *
 * @author Grayson Kuhns
 */
public interface PluginListOutputWriter {

  /**
   * Gets the {@link PluginListOutputFormat}.
   *
   * @return The {@link PluginListOutputFormat}.
   */
  PluginListOutputFormat getFormat();

  /**
   * Writes a {@link Plugin} {@link List}.
   *
   * @param plugins The {@link Plugin}s.
   * @param output The {@link java.io.OutputStream}.
   * @throws OutputWriteException If an error occurs while writing the output.
   */
  void write(List<Plugin> plugins, PrintStream output) throws OutputWriteException;
}
