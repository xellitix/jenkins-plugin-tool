package com.xellitix.jenkins.plugintool.output;

/**
 * {@link PluginListOutputWriter} locator.
 *
 * @author Grayson Kuhns
 */
public interface PluginListOutputWriterLocator {

  /**
   * Gets a {@link PluginListOutputWriter} for a given {@link PluginListOutputFormat}.
   *
   * @param format The {@link PluginListOutputFormat}.
   * @return The {@link PluginListOutputWriter}.
   * @throws OutputWriteException If a {@link PluginListOutputWriter} is unable to be found.
   */
  PluginListOutputWriter locate(PluginListOutputFormat format) throws OutputWriteException;
}
