package com.xellitix.jenkins.plugintool.cli.converter;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUserFactory;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link JenkinsApiUser} {@link ParameterConverter}.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class JenkinsApiUserConverter implements ParameterConverter<JenkinsApiUser> {

  // Constants
  private static final String MALFORMED_MSG = "Jenkins authentication string is malformed";

  // Dependencies
  private final JenkinsApiUserFactory apiUserFactory;

  /**
   * Constructor.
   *
   * @param apiUserFactory The {@link JenkinsApiUserFactory}.
   */
  @Inject
  JenkinsApiUserConverter(final JenkinsApiUserFactory apiUserFactory) {
    this.apiUserFactory = apiUserFactory;
  }

  /**
   * Gets the conversion type.
   *
   * @return The conversion type.
   */
  @Override
  public Class<JenkinsApiUser> getConversionType() {
    return JenkinsApiUser.class;
  }

  /**
   * Converts a {@link JenkinsApiUser}.
   *
   * @param input The input.
   * @return The {@link JenkinsApiUser}.
   */
  @Override
  public JenkinsApiUser convert(final String input) {
    // Split the auth string into parts
    final String[] parts = input.split(":");

    if (parts.length != 2) {
      throw new ParameterConversionException(MALFORMED_MSG);
    }

    // Extract auth components
    final String username = parts[0].trim();
    final String apiToken = parts[1].trim();

    // Validate auth components
    if (username.isEmpty() || apiToken.isEmpty()) {
      throw new ParameterConversionException(MALFORMED_MSG);
    }

    // Create the API user
    return apiUserFactory.create(username, apiToken);
  }
}
