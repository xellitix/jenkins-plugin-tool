package com.xellitix.jenkins.plugintool.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * JSON {@link PluginListOutputWriter} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class JsonPluginListOutputWriter implements PluginListOutputWriter {

  // Dependencies
  private final ObjectMapper objectMapper;

  /**
   * Constructor.
   *
   * @param objectMapper The {@link ObjectMapper}.
   */
  @Inject
  JsonPluginListOutputWriter(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  /**
   * Gets the {@link PluginListOutputFormat}.
   *
   * @return The {@link PluginListOutputFormat}.
   */
  @Override
  public PluginListOutputFormat getFormat() {
    return PluginListOutputFormat.JSON;
  }

  /**
   * Writes a {@link Plugin} {@link List}.
   *
   * @param plugins The {@link Plugin}s.
   * @param output The {@link OutputStream}.
   * @throws OutputWriteException If an error occurs while writing the output.
   */
  @Override
  public void write(
      final List<Plugin> plugins,
      final PrintStream output)
      throws OutputWriteException {

    try {
      objectMapper.writeValue(output, plugins);
    } catch (IOException ex) {
      throw new OutputWriteException(ex);
    }
  }
}
