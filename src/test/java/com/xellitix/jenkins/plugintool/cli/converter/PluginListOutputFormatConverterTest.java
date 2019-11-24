package com.xellitix.jenkins.plugintool.cli.converter;

import com.xellitix.jenkins.plugintool.output.PluginListOutputFormat;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link PluginListOutputFormatConverter} test case.
 *
 * @author Garrett Ewens
 */
public class PluginListOutputFormatConverterTest {

  // Constants
  private static final String FORMAT_VALID = "JSON";
  private static final String FORMAT_INVALID = "?%$^lklksdff";
  private static final String INVALID_FORMAT_MSG = "Invalid output format given";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private PluginListOutputFormatConverter formatConverter;

  @Test
  public void getConversionType__Test() {
    assertThat(formatConverter
        .getConversionType())
        .isNotNull()
        .isEqualTo(PluginListOutputFormat.class);
  }

  @Test
  public void convert__ParsesTheOutputFormat__WhenInputIsValid__Test() {
     assertThat(formatConverter
        .convert(FORMAT_VALID))
        .isNotNull()
        .isEqualTo(PluginListOutputFormat.JSON);
  }

  @Test
  public void convert__ThrowsException__WhenInputIsInvalid__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConversionException.class);
    thrown.expectMessage(INVALID_FORMAT_MSG);

    // Attempt to convert an invalid URI
    formatConverter.convert(FORMAT_INVALID);
  }

  @Before
  public void setUp() {
    formatConverter = new PluginListOutputFormatConverter();
  }
}
