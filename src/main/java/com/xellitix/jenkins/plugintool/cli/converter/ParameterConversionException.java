package com.xellitix.jenkins.plugintool.cli.converter;

/**
 * {@link com.beust.jcommander.Parameter} conversion exception.
 *
 * @author Grayson Kuhns
 */
public class ParameterConversionException extends RuntimeException {

  /**
   * Constructor.
   *
   * @param cause The cause.
   */
  public ParameterConversionException(final Throwable cause) {
    super(cause);
  }
}
