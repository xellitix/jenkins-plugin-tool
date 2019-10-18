package com.xellitix.jenkins.plugintool.cli.command;

import static org.assertj.core.api.Assertions.assertThat;

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

  @Before
  public void setUp() throws Exception {
    // Create the Jenkins endpoint URI
    jenkinsEndpoint = new URI(JENKINS_ENDPOINT);

    // Create the command
    command = new FetchInstalledPluginsCommand();

    // Set the jenkins endpoint (this will be done by jCommander in production)
    setPrivateUri(command, "jenkinsEndpoint", jenkinsEndpoint);
  }

  private void setPrivateUri(final Object obj, final String field, final URI uri) throws Exception {
    // Get the field
    final Field uriField = obj.getClass().getDeclaredField(field);

    // Enable h4x mode
    uriField.setAccessible(true);

    // Set the field value
    uriField.set(obj, uri);
  }
}
