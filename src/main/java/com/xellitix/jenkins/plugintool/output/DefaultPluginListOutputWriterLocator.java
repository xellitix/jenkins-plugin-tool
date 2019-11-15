package com.xellitix.jenkins.plugintool.output;

import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Default {@link PluginListOutputWriterLocator} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultPluginListOutputWriterLocator implements PluginListOutputWriterLocator {

  // Dependencies
  private final Set<PluginListOutputWriter> outputWriters;

  /**
   * Constructor.
   *
   * @param outputWriters The {@link PluginListOutputWriter}s.
   */
  @Inject
  DefaultPluginListOutputWriterLocator(final Set<PluginListOutputWriter> outputWriters) {
    this.outputWriters = outputWriters;
  }

  /**
   * Gets a {@link PluginListOutputWriter} for a given {@link PluginListOutputFormat}.
   *
   * @param format The {@link PluginListOutputFormat}.
   * @return The {@link PluginListOutputWriter}.
   * @throws OutputWriteException If a {@link PluginListOutputWriter} is unable to be found.
   */
  @Override
  public PluginListOutputWriter locate(
      final PluginListOutputFormat format)
      throws OutputWriteException {

    return outputWriters
        .stream()
        .filter(writer -> writer
            .getFormat()
            .equals(format))
        .findFirst()
        .orElseThrow(() -> new OutputWriteException(format));
  }
}
