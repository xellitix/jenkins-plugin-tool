package com.xellitix.jenkins.plugintool.cli.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.beust.jcommander.Parameter;
import com.google.common.collect.ImmutableSet;
import java.net.URI;
import java.net.URL;
import java.util.Set;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link ParameterConverterLocator} test case.
 *
 * @author Grayson Kuhns
 */
public class ParameterConverterLocatorTest {

  // Constants
  private static final String OPTION_NAME = "optionName";
  private static final String NOT_FOUND_MSG =
      "A ParameterConverter could not be found for type: \"java.net.URL\"";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private Parameter parameter;
  private UriTestConverter uriConverter;
  private ParameterConverterLocator converterLocator;

  @Test
  public void getConverterInstance__ReturnsParameterConverter__WhenParameterConverterIsRegisteredForType__Test() {
    assertThat(converterLocator
        .getConverterInstance(parameter, URI.class, OPTION_NAME))
        .isNotNull()
        .isEqualTo(uriConverter);
  }

  @Test
  public void getConverterInstance__ThrowsException__WhenParameterConverterIsNotRegisteredForType__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConverterNotFoundException.class);
    thrown.expectMessage(NOT_FOUND_MSG);

    // Attempt to get a converter for an unregistered type
    converterLocator.getConverterInstance(parameter, URL.class, OPTION_NAME);
  }

  @Before
  public void setUp() {
    // Parameter mocking
    parameter = mock(Parameter.class);

    // Create the converters
    uriConverter = new UriTestConverter();
    Set<ParameterConverter> converters = ImmutableSet.of(uriConverter);

    // Create the converter locator
    converterLocator = new ParameterConverterLocator(converters);
  }

  /**
   * Test {@link ParameterConverter}.
   */
  private static class UriTestConverter implements ParameterConverter<URI> {

    @Override
    public URI convert(final String value) {
      return null;
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
}
