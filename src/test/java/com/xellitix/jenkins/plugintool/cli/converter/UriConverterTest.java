package com.xellitix.jenkins.plugintool.cli.converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link UriConverter} test case.
 *
 * @author Grayson Kuhns
 */
public class UriConverterTest {

  // Constants
  private static final String URI_VALID = "https://foobar.local";
  private static final String URI_INVALID = "?%$^lklksdff";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private UriConverter uriConverter;

  @Test
  public void getConversionType__ReturnsUri__Test() {
    assertThat(uriConverter
        .getConversionType())
        .isNotNull()
        .isEqualTo(URI.class);
  }

  @Test
  public void convert__ReturnsEquivalentUri__WhenInputIsValid__Test() {
    final URI uri = uriConverter.convert(URI_VALID);

    assertThat(uri).isNotNull();
    assertThat(uri
        .toASCIIString())
        .isEqualTo(URI_VALID);
  }

  @Test
  public void convert__ThrowsException__WhenInputIsInvalid__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConversionException.class);
    thrown.expectCause(IsInstanceOf.instanceOf(URISyntaxException.class));

    // Attempt to convert an invalid URI
    uriConverter.convert(URI_INVALID);
  }

  @Before
  public void setUp() {
    uriConverter = new UriConverter();
  }
}
