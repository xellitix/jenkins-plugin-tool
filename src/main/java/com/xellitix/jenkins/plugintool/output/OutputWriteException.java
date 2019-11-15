package com.xellitix.jenkins.plugintool.output;

/**
 * Exception that occurred while writing output.
 *
 * @author Grayson Kuhns
 */
public class OutputWriteException extends RuntimeException {

  // Constants
  private static final String MSG_TEMPLATE_FORMAT_NOT_FOUND =
      "A %s could not be found for format %s";

  /**
   * Constructor.
   *
   * @param cause The cause of the failure.
   */
  public OutputWriteException(final Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   *
   * @param unsatisfiedFormat The {@link PluginListOutputFormat} for which
   *     a {@link PluginListOutputWriter} could not be found.
   */
  public OutputWriteException(final PluginListOutputFormat unsatisfiedFormat) {
    super(String.format(
        MSG_TEMPLATE_FORMAT_NOT_FOUND,
        PluginListOutputWriter.class.getSimpleName(),
        unsatisfiedFormat.toString()));
  }
}
