package com.xellitix.jenkins.plugintool.cli.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.output.PluginListOutputFormat;
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
  public void getJenkinsApiUser__Test() {
    assertThat(command
        .getJenkinsApiUser())
        .isPresent()
        .get()
        .isEqualTo(apiUser);
  }

  @Test
  public void getOutputFormat__ReturnsConfiguredFormat__WhenFormatIsConfigured__Test() throws Exception {
    // Prepare the test
    setPrivateProperty(command, "outputFormat", PluginListOutputFormat.YAML);

    // Get the output format
    assertThat(command
        .getOutputFormat())
        .isNotNull()
        .isEqualTo(PluginListOutputFormat.YAML);
  }

  @Test
  public void getOutputFormat__ReturnsDefaultFormat__WhenFormatIsNotConfigured__Test() {
    assertThat(command
        .getOutputFormat())
        .isNotNull()
        .isEqualTo(PluginListOutputFormat.JENKINS);
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
