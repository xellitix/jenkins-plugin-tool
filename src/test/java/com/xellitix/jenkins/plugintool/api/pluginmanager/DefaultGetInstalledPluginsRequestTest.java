package com.xellitix.jenkins.plugintool.api.pluginmanager;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import java.net.URI;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultGetInstalledPluginsRequest} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultGetInstalledPluginsRequestTest {

  // Constants
  private static final String API_ENDPOINT = "http://localhost:8080";

  // Fixtures
  private URI apiEndpoint;
  private JenkinsApiUser apiUser;
  private DefaultGetInstalledPluginsRequest request;

  @Test
  public void getApiEndpoint__Test() {
    assertThat(request
        .getApiEndpoint())
        .isNotNull()
        .isEqualTo(apiEndpoint);
  }

  @Test
  public void getApiUser__Test() {
    assertThat(request
        .getApiUser())
        .isNotNull()
        .isEqualTo(apiUser);
  }

  @Before
  public void setUp() throws Exception {
    // Create the endpoint
    apiEndpoint = new URI(API_ENDPOINT);

    // Mock the API user
    apiUser = mock(JenkinsApiUser.class);

    // Create the plugins request
    request = new DefaultGetInstalledPluginsRequest(apiEndpoint, apiUser);
  }
}
