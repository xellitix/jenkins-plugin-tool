package com.xellitix.jenkins.plugintool.api.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultJenkinsApiUser} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultJenkinsApiUserTest {

  // Constants
  private static final String USERNAME = "user";
  private static final String API_TOKEN = "98as7j8fd76asf";

  // Fixtures
  private DefaultJenkinsApiUser apiUser;

  @Test
  public void getUsername__Test() {
    assertThat(apiUser
        .getUsername())
        .isNotNull()
        .isEqualTo(USERNAME);
  }

  @Test
  public void getApiToken__Test() {
    assertThat(apiUser
        .getApiToken())
        .isNotNull()
        .isEqualTo(API_TOKEN);
  }

  @Before
  public void setUp() {
    apiUser = new DefaultJenkinsApiUser(USERNAME, API_TOKEN);
  }
}
