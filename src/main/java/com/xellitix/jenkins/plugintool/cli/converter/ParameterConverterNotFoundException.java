package com.xellitix.jenkins.plugintool.cli.converter;

/**
 * {@link ParameterConverter} not found exception.
 *
 * @author Grayson Kuhns
 */
public class ParameterConverterNotFoundException extends RuntimeException {

  // Constants
  private static final String MSG_TEMPLATE =
      "A ParameterConverter could not be found for type: \"%s\"";

  /**
   * Constructor.
   *
   * @param conversionType The type for which a {@link ParameterConverter} could not be found.
   */
  public ParameterConverterNotFoundException(final Class conversionType) {
    super(String.format(MSG_TEMPLATE, conversionType.getName()));
  }
}
