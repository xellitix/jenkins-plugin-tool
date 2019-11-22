package com.xellitix.jenkins.plugintool.cli.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import java.lang.reflect.Field;
import java.net.URI;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link FetchInstalledPluginsCommand} test case.
 *
 * @author Grayson Kuhns
 */
public class FetchInstalledPluginsCommandTest {

  // Constants
  private static final String NAME = "fetch-installed-plugins";
  private static final String JENKINS_ENDPOINT = "https://jenkins.local";

  // Fixtures
  private URI jenkinsEndpoint;
  private JenkinsApiUser apiUser;

  private FetchInstalledPluginsCommand command;

  @Test
  public void getJenkinsEndpoint__Test() {
    assertThat(command
        .getJenkinsEndpoint())
        .isNotNull()
        .isEqualTo(jenkinsEndpoint);
  }

  @Test
  public void getName__Test() {
    assertThat(command
        .getName())
        .isNotNull()
        .isEqualTo(NAME);
  }

  @Test
  public void getJenkinsApiUserTest__Test() {
    assertThat(command
        .getJenkinsApiUser())
        .isPresent()
        .get()
        .isEqualTo(apiUser);
  }

  @Before
  public void setUp() throws Exception {
    // Create the Jenkins endpoint URI
    jenkinsEndpoint = new URI(JENKINS_ENDPOINT);

    // Mock the API user
    apiUser = mock(JenkinsApiUser.class);

    // Create the command
    command = new FetchInstalledPluginsCommand();

    // Set the jenkins endpoint (this will be done by jCommander in production)
    setPrivateProperty(command, "jenkinsEndpoint", jenkinsEndpoint);
    setPrivateProperty(command, "jenkinsApiUser", apiUser);
  }

  private void setPrivateProperty(final Object obj, final String field, final Object value) throws Exception {
    // Get the field
    final Field uriField = obj.getClass().getDeclaredField(field);

    // Enable h4x mode
    uriField.setAccessible(true);

    // Set the field value
    uriField.set(obj, value);
  }
}
