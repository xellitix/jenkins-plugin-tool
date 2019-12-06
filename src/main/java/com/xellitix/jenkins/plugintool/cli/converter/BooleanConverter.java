package com.xellitix.jenkins.plugintool.cli.converter;

/**
 * {@link Boolean} {@link ParameterConverter}.
 *
 * @author Grayson Kuhns
 */
public class BooleanConverter
    extends com.beust.jcommander.converters.BooleanConverter
    implements ParameterConverter<Boolean> {

  /**
   * Constructor.
   */
  BooleanConverter() {
    super("Option Name Unknown");
  }

  /**
   * Gets the conversion type.
   *
   * @return The conversion type.
   */
  @Override
  public Class<Boolean> getConversionType() {
    return Boolean.class;
  }
}
