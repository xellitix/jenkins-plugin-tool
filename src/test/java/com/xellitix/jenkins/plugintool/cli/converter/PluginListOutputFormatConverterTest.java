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

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  // Fixtures
  private PluginListOutputFormatConverter pluginList;

  @Test
  public void getConversionType__Test() {
    assertThat(pluginList
        .getConversionType())
        .isNotNull()
        .isEqualTo(PluginListOutputFormat.class);
  }

  @Test
  public void convert__ReturnsEquivalentPluginList__WhenInputIsValid__Test() {
    final PluginListOutputFormat format = pluginList.convert(FORMAT_VALID);

    assertThat(format).isNotNull();
    assertThat(format
        .toString())
        .isEqualTo(FORMAT_VALID);
  }

  @Test
  public void convert__ThrowsException__WhenInputIsInvalid__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConversionException.class);
    thrown.expectCause(IsInstanceOf.instanceOf(IllegalArgumentException.class));

    // Attempt to convert an invalid URI
    pluginList.convert(FORMAT_INVALID);
  }

  @Before
  public void setUp() {
    pluginList = new PluginListOutputFormatConverter();
  }
}
