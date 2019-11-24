package com.xellitix.jenkins.plugintool.api.pluginmanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.google.inject.Provider;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import java.net.URI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * {@link CdancyJenkinsClientFactory} test case.
 *
 * @author Grayson Kuhns
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JenkinsClient.class)
public class CdancyJenkinsClientFactoryTest {

  // Constants
  private static final String USERNAME =
      "admin";
  private static final String API_TOKEN =
      "89a7s87d6a67s5d76as786d";
  private static final String EXPECTED_AUTH_STRING =
      "admin:89a7s87d6a67s5d76as786d";

  private static final String API_ENDPOINT =
      "http://localhost:8080";

  // Fixtures
  private URI apiEndpoint;
  private JenkinsApiUser apiUser;

  private JenkinsClient client;
  private JenkinsClient.Builder clientBuilder;
  private Provider<JenkinsClient.Builder> clientBuilderProvider;

  private CdancyJenkinsClientFactory clientFactory;

  @Test
  public void create__Test() {
    assertThat(clientFactory
        .create(apiEndpoint, apiUser))
        .isNotNull()
        .isEqualTo(client);

    verify(clientBuilder).credentials(eq(EXPECTED_AUTH_STRING));
    verify(clientBuilder).endPoint(eq(API_ENDPOINT));
  }

  @Before
  public void setUp() throws Exception {
    // Create Jenkins endpoint
    apiEndpoint = new URI(API_ENDPOINT);

    // API user mocking
    apiUser = mock(JenkinsApiUser.class);
    doReturn(USERNAME)
        .when(apiUser)
        .getUsername();
    doReturn(API_TOKEN)
        .when(apiUser)
        .getApiToken();

    // Jenkins client mocking
    client = mock(JenkinsClient.class);
    clientBuilder = mock(JenkinsClient.Builder.class);
    doReturn(clientBuilder)
        .when(clientBuilder)
        .endPoint(anyString());
    doReturn(clientBuilder)
        .when(clientBuilder)
        .credentials(anyString());
    doReturn(client)
        .when(clientBuilder)
        .build();

    clientBuilderProvider = mock(Provider.class);
    doReturn(clientBuilder)
        .when(clientBuilderProvider)
        .get();

    // Create the client factory
    clientFactory = new CdancyJenkinsClientFactory(clientBuilderProvider);
  }
}
