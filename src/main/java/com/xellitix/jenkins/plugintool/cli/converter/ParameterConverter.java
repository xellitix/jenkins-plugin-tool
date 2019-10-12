package com.xellitix.jenkins.plugintool.cli.converter;

import com.beust.jcommander.IStringConverter;

/**
 * Parameter converter.
 *
 * @param <T> The parameter type.
 * @author Grayson Kuhns
 */
public interface ParameterConverter<T> extends IStringConverter<T> {

  /**
   * Gets the conversion type.
   *
   * @return The conversion type.
   */
  Class<T> getConversionType();
}
