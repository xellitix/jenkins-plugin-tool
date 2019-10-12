package com.xellitix.jenkins.plugintool.cli.converter;

import com.beust.jcommander.Parameter;

/**
 * {@link Parameter} conversion exception.
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
