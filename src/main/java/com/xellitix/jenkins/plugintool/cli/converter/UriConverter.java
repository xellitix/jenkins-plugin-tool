package com.xellitix.jenkins.plugintool.cli.converter;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * {@link URI} converter.
 *
 * <p>
 * Used for converting command line parameters parsed by JCommander.
 * </p>
 *
 * @author Grayson Kuhns
 */
public class UriConverter implements ParameterConverter<URI> {

  /**
   * Creates a {@link URI} from a {@link String}.
   *
   * @param uri The {@link String} to convert.
   * @return The {@link URI}.
   * @throws ParameterConversionException If an error occurs.
   */
  @Override
  public URI convert(final String uri) throws ParameterConversionException {
    try {
      return new URI(uri);
    } catch (URISyntaxException ex) {
      throw new ParameterConversionException(ex);
    }
  }

  /**
   * Gets the conversion type.
   *
   * @return The conversion type.
   */
  @Override
  public Class<URI> getConversionType() {
    return URI.class;
  }
}
