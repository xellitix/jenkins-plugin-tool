package com.xellitix.jenkins.plugintool.cli.converter;

import com.xellitix.jenkins.plugintool.output.PluginListOutputFormat;

import java.util.Arrays;

/**
 * {@link PluginListOutputFormat} converter.
 *
 * <p>
 * used to parse a {@link PluginListOutputFormat} specified by the user.
 * </p>
 *
 * @author Garrett Ewens
 */
public class PluginListOutputFormatConverter implements ParameterConverter<PluginListOutputFormat> {

  /**
   * Creates a {@link PluginListOutputFormat} from a {@link String}.
   *
   * @param format The {@link String} to convert.
   * @return The {@link PluginListOutputFormat}.
   * @throws ParameterConversionException If an error occurs.
   */
  @Override
  public PluginListOutputFormat convert(final String format) throws ParameterConversionException {
    return Arrays
        .stream(PluginListOutputFormat.values())
        .filter(value -> value
            .toString()
            .toLowerCase()
            .equals(format.toLowerCase()))
        .findFirst()
        .orElseThrow(() ->
            new ParameterConversionException(new IllegalArgumentException("Invalid output format given.")));
  }

  /**
   * Gets the conversion type.
   *
   * @return The conversion type.
   */
  @Override
  public Class<PluginListOutputFormat> getConversionType() {
    return PluginListOutputFormat.class;
  }
}
