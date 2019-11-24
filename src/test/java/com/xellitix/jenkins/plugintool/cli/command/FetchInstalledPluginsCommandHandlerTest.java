package com.xellitix.jenkins.plugintool.cli.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUserRetriever;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link FetchInstalledPluginsCommandHandler} test case.
 *
 * @author Grayson Kuhns
 */
public class FetchInstalledPluginsCommandHandlerTest {

  // Fixtures
  private JenkinsApiUserRetriever apiUserRetriever;
  private FetchInstalledPluginsCommandHandler handler;

  @Test
  public void getHandledType__ReturnsFetchInstalledPluginsCommand__Test() {
    assertThat(handler
        .getHandledType())
        .isNotNull()
        .isEqualTo(FetchInstalledPluginsCommand.class);
  }

  @Before
  public void setUp() {
    // Mock the API user retriever
    apiUserRetriever = mock(JenkinsApiUserRetriever.class);

    // Create the command handler
    handler = new FetchInstalledPluginsCommandHandler(apiUserRetriever);
  }
}
