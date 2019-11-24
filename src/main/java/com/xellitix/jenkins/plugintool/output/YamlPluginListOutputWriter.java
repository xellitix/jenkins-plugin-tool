package com.xellitix.jenkins.plugintool.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.Inject;
import com.xellitix.jenkins.plugintool.plugin.Plugin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * YAML {@link PluginListOutputWriter} implementation.
 *
 * @author Garrett Ewens
 */
public class YamlPluginListOutputWriter implements PluginListOutputWriter {

  // Dependencies
  private final ObjectMapper objectMapper;

  /**
   * Constructor.
   */
  @Inject
  YamlPluginListOutputWriter() {
    this.objectMapper = new ObjectMapper(new YAMLFactory());
    this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  /**
   * Gets the {@link PluginListOutputFormat}.
   *
   * @return The {@link PluginListOutputFormat}.
   */
  @Override
  public PluginListOutputFormat getFormat() {
    return PluginListOutputFormat.YAML;
  }

  /**
   * Writes a {@link Plugin} {@link List}.
   *
   * @param plugins The {@link Plugin}s.
   * @param output  The {@link OutputStream}.
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
