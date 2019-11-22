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
 * {@link DefaultJenkinsApiUserRetriever} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultJenkinsApiUserRetrieverTest {

  // Constants
  private static final String USERNAME = "admin";
  private static final String API_TOKEN = "908a8s87d676as5d65a4s";

  private static final String ENVVAR_USERNAME = "JENKINS_API_USER";
  private static final String ENVVAR_API_TOKEN = "JENKINS_API_TOKEN";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private SystemProxy system;

  private JenkinsApiUser commandApiUser;
  private JenkinsApiUser envApiUser;
  private JenkinsApiUserFactory apiUserFactory;

  private FetchInstalledPluginsCommand command;
  private DefaultJenkinsApiUserRetriever apiUserRetriever;

  @Test
  public void get__ReturnsCommandUser__IfPresent__Test() {
    // Prepare the test
    doReturn(Optional.of(commandApiUser))
        .when(command)
        .getJenkinsApiUser();

    // Attempt to get the user
    assertThat(apiUserRetriever
        .get(command))
        .isNotNull()
        .isEqualTo(commandApiUser);
  }

  @Test
  public void get__ReturnsEnvUser__IfCommandUserNotPresent__AndEnvUserIsValid__Test() {
    // Prepare the test
    doReturn(USERNAME)
        .when(system)
        .getEnv(eq(ENVVAR_USERNAME));
    doReturn(API_TOKEN)
        .when(system)
        .getEnv(eq(ENVVAR_API_TOKEN));

    // Attempt to get the user
    assertThat(apiUserRetriever
        .get(command))
        .isNotNull()
        .isEqualTo(envApiUser);
  }

  @Test
  public void get__ThrowsException__IfCommandUserNotPresent__AndEnvUsernameIsNotSet__Test() {
    // Prepare the test
    doReturn(null)
        .when(system)
        .getEnv(eq(ENVVAR_USERNAME));
    doReturn(API_TOKEN)
        .when(system)
        .getEnv(eq(ENVVAR_API_TOKEN));

    // Describe the exception to expect
    thrown.expect(JenkinsApiUserNotFoundException.class);

    // Attempt to get the user
    apiUserRetriever.get(command);
  }

  @Test
  public void get__ThrowsException__IfCommandUserNotPresent__AndEnvUsernameIsEmpty__Test() {
    // Prepare the test
    doReturn("")
        .when(system)
        .getEnv(eq(ENVVAR_USERNAME));
    doReturn(API_TOKEN)
        .when(system)
        .getEnv(eq(ENVVAR_API_TOKEN));

    // Describe the exception to expect
    thrown.expect(JenkinsApiUserNotFoundException.class);

    // Attempt to get the user
    apiUserRetriever.get(command);
  }

  @Test
  public void get__ThrowsException__IfCommandUserNotPresent__AndEnvApiTokenIsNotSet__Test() {
    // Prepare the test
    doReturn(USERNAME)
        .when(system)
        .getEnv(eq(ENVVAR_USERNAME));
    doReturn(null)
        .when(system)
        .getEnv(eq(ENVVAR_API_TOKEN));

    // Describe the exception to expect
    thrown.expect(JenkinsApiUserNotFoundException.class);

    // Attempt to get the user
    apiUserRetriever.get(command);
  }

  @Test
  public void get__ThrowsException__IfCommandUserNotPresent__AndEnvApiTokenIsEmpty__Test() {
    // Prepare the test
    doReturn(USERNAME)
        .when(system)
        .getEnv(eq(ENVVAR_USERNAME));
    doReturn("")
        .when(system)
        .getEnv(eq(ENVVAR_API_TOKEN));

    // Describe the exception to expect
    thrown.expect(JenkinsApiUserNotFoundException.class);

    // Attempt to get the user
    apiUserRetriever.get(command);
  }

  @Before
  public void setUp() {
    // System mocking
    system = mock(SystemProxy.class);

    // API user mocking
    commandApiUser = mock(JenkinsApiUser.class);
    envApiUser = mock(JenkinsApiUser.class);
    apiUserFactory = mock(JenkinsApiUserFactory.class);
    doReturn(envApiUser)
        .when(apiUserFactory)
        .create(eq(USERNAME), eq(API_TOKEN));

    // Command mocking
    command = mock(FetchInstalledPluginsCommand.class);
    doReturn(Optional.empty())
        .when(command)
        .getJenkinsApiUser();

    // Create the API user retriever
    apiUserRetriever = new DefaultJenkinsApiUserRetriever(system, apiUserFactory);
  }
}
