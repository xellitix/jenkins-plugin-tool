package com.xellitix.jenkins.plugintool.authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.xellitix.jenkins.plugintool.cli.command.FetchInstalledPluginsCommand;
import com.xellitix.jenkins.plugintool.system.SystemProxy;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link DefaultJenkinsApiTokenRetriever} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultJenkinsApiTokenRetrieverTest {

  // Constants
  private static final String COMMAND_API_TOKEN = "o8a6s6d7d7as76d5";
  private static final String ENVVAR_API_TOKEN = "7asd76aisd87aosd7o";
  private static final String ENVVAR_NAME = "JENKINS_API_TOKEN";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private FetchInstalledPluginsCommand command;
  private SystemProxy system;
  private DefaultJenkinsApiTokenRetriever apiTokenRetriever;

  @Test
  public void getApiToken__ReturnsTheCommandApiToken__WhenTheCommandApiTokenIsValid__Test() {
    // Prepare the test
    doReturn(Optional.of(COMMAND_API_TOKEN))
        .when(command)
        .getApiToken();

    // Attempt to get the API token
    assertThat(apiTokenRetriever
        .getApiToken(command))
        .isNotNull()
        .isEqualTo(COMMAND_API_TOKEN);
  }

  @Test
  public void getApiToken__ReturnsTheEnvVarApiToken__WhenTheCommandApiTokenIsNotPresent__Test() {
    // Prepare the test
    doReturn(ENVVAR_API_TOKEN)
        .when(system)
        .getEnv(eq(ENVVAR_NAME));

    // Attempt to get the API token
    assertThat(apiTokenRetriever
        .getApiToken(command))
        .isNotNull()
        .isEqualTo(ENVVAR_API_TOKEN);
  }

  @Test
  public void getApiToken__ThrowsException__WhenNoApiTokenIsPresent__Test() {
    // Describe the exception to expect
    thrown.expect(JenkinsApiTokenNotFoundException.class);

    // Attempt to get the API token
    apiTokenRetriever.getApiToken(command);
  }

  @Test
  public void getApiToken__ThrowsException__WhenNoApiTokenIsValid__Test() {
    // Prepare the test
    doReturn(Optional.of(""))
        .when(command)
        .getApiToken();
    doReturn("")
        .when(system)
        .getEnv(eq(ENVVAR_NAME));

    // Describe the exception to expect
    thrown.expect(JenkinsApiTokenNotFoundException.class);

    // Attempt to get the API token
    apiTokenRetriever.getApiToken(command);
  }

  @Before
  public void setUp() {
    // Command mocking
    command = mock(FetchInstalledPluginsCommand.class);
    doReturn(Optional.empty())
        .when(command)
        .getApiToken();

    // System mocking
    system = mock(SystemProxy.class);

    // Create the API token retriever
    apiTokenRetriever = new DefaultJenkinsApiTokenRetriever(system);
  }
}
