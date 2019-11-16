package com.xellitix.jenkins.plugintool.cli.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Optional;

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
  private static final String API_TOKEN = "api-token";

  // Fixtures
  private URI jenkinsEndpoint;
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
  public void getApiToken__Test() {
    Optional<String> var = command.getApiToken();
    assertThat(var)
        .isPresent()
        .get()
        .isEqualTo(API_TOKEN);
  }

  @Before
  public void setUp() throws Exception {
    // Create the Jenkins endpoint URI
    jenkinsEndpoint = new URI(JENKINS_ENDPOINT);

    // Create the command
    command = new FetchInstalledPluginsCommand();

    // Set the jenkins endpoint (this will be done by jCommander in production)
    setPrivateProperty(command, "jenkinsEndpoint", jenkinsEndpoint);
    setPrivateProperty(command, "apiToken", API_TOKEN);
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
