package com.xellitix.jenkins.plugintool.cli.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUserFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link JenkinsApiUserConverter} test case.
 *
 * @author Grayson Kuhns
 */
public class JenkinsApiUserConverterTest {

  // Constants
  private static final String MALFORMED_MSG =
      "Jenkins authentication string is malformed";

  private static final String USERNAME =
      "admin";
  private static final String API_TOKEN =
      "09a8s9d8a76s5d564as";

  private static final String AUTH_STRING_VALID =
      USERNAME.concat(":").concat(API_TOKEN);
  private static final String AUTH_STRING_INVALID_EMPTY =
      "";
  private static final String AUTH_STRING_INVALID_ONE_SECTION =
      API_TOKEN;
  private static final String AUTH_STRING_INVALID_NO_USERNAME =
      " :".concat(API_TOKEN);
  private static final String AUTH_STRING_INVALID_NO_API_TOKEN =
      USERNAME.concat(": ");

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private JenkinsApiUser apiUser;
  private JenkinsApiUserFactory apiUserFactory;

  private JenkinsApiUserConverter converter;

  @Test
  public void getConversionType__Test() {
    assertThat(converter
        .getConversionType()
        .equals(JenkinsApiUser.class));
  }

  @Test
  public void convert__ReturnsTheApiUser__WhenAuthStringIsValid__Test() {
    assertThat(converter
        .convert(AUTH_STRING_VALID))
        .isNotNull()
        .isEqualTo(apiUser);
  }

  @Test
  public void convert__ThrowsException__WhenAuthStringIsEmpty__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConversionException.class);
    thrown.expectMessage(MALFORMED_MSG);

    // Attempt to convert the auth string
    converter.convert(AUTH_STRING_INVALID_EMPTY);
  }

  @Test
  public void convert__ThrowsException__WhenAuthStringHasOneSection__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConversionException.class);
    thrown.expectMessage(MALFORMED_MSG);

    // Attempt to convert the auth string
    converter.convert(AUTH_STRING_INVALID_ONE_SECTION);
  }

  @Test
  public void convert__ThrowsException__WhenAuthStringIsMissingUsername__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConversionException.class);
    thrown.expectMessage(MALFORMED_MSG);

    // Attempt to convert the auth string
    converter.convert(AUTH_STRING_INVALID_NO_USERNAME);
  }

  @Test
  public void convert__ThrowsException__WhenAuthStringIsMissingApiToken__Test() {
    // Describe the exception to expect
    thrown.expect(ParameterConversionException.class);
    thrown.expectMessage(MALFORMED_MSG);

    // Attempt to convert the auth string
    converter.convert(AUTH_STRING_INVALID_NO_API_TOKEN);
  }

  @Before
  public void setUp() {
    // API user mocking
    apiUser = mock(JenkinsApiUser.class);
    apiUserFactory = mock(JenkinsApiUserFactory.class);
    doReturn(apiUser)
        .when(apiUserFactory)
        .create(eq(USERNAME), eq(API_TOKEN));

    // Create the API user converter
    converter = new JenkinsApiUserConverter(apiUserFactory);
  }
}
