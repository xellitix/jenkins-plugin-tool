package com.xellitix.jenkins.plugintool.cli.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.collect.ImmutableList;
import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequest;
import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequestExecutor;
import com.xellitix.jenkins.plugintool.api.pluginmanager.GetInstalledPluginsRequestFactory;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUser;
import com.xellitix.jenkins.plugintool.authentication.JenkinsApiUserRetriever;
import com.xellitix.jenkins.plugintool.output.PluginListOutputFormat;
import com.xellitix.jenkins.plugintool.output.PluginListOutputWriter;
import com.xellitix.jenkins.plugintool.output.PluginListOutputWriterLocator;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import java.io.PrintStream;
import java.net.URI;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link FetchInstalledPluginsCommandHandler} test case.
 *
 * @author Grayson Kuhns
 */
public class FetchInstalledPluginsCommandHandlerTest {

  // Constants
  private static final String JENKINS_ENDPOINT =
      "http://localhost:8080";

  private static final PluginListOutputFormat OUTPUT_FORMAT =
      PluginListOutputFormat.JENKINS;

  // Fixtures
  private URI jenkinsEndpoint;
  private FetchInstalledPluginsCommand command;

  private JenkinsApiUser apiUser;
  private JenkinsApiUserRetriever apiUserRetriever;

  private GetInstalledPluginsRequest request;
  private GetInstalledPluginsRequestFactory requestFactory;

  private List<Plugin> plugins;
  private GetInstalledPluginsRequestExecutor requestExecutor;

  private PluginListOutputWriter outputWriter;
  private PluginListOutputWriterLocator outputWriterLocator;

  private FetchInstalledPluginsCommandHandler commandHandler;

  @Test
  public void getHandledType__ReturnsFetchInstalledPluginsCommand__Test() {
    assertThat(commandHandler
        .getHandledType())
        .isNotNull()
        .isEqualTo(FetchInstalledPluginsCommand.class);
  }

  @Test
  public void handle__HandlesTheCommand__Test() {
    // Attempt to perform the task
    commandHandler.handle(command);

    // Verify that the output was written
    verify(outputWriter).write(eq(plugins), any(PrintStream.class));
  }

  @Before
  public void setUp() throws Exception {
    // Command mocking
    jenkinsEndpoint = new URI(JENKINS_ENDPOINT);
    command = mock(FetchInstalledPluginsCommand.class);
    doReturn(jenkinsEndpoint)
        .when(command)
        .getJenkinsEndpoint();
    doReturn(OUTPUT_FORMAT)
        .when(command)
        .getOutputFormat();

    // API user mocking
    apiUser = mock(JenkinsApiUser.class);
    apiUserRetriever = mock(JenkinsApiUserRetriever.class);
    doReturn(apiUser)
        .when(apiUserRetriever)
        .get(eq(command));

    // Action request mocking
    request = mock(GetInstalledPluginsRequest.class);
    requestFactory = mock(GetInstalledPluginsRequestFactory.class);
    doReturn(request)
        .when(requestFactory)
        .create(eq(jenkinsEndpoint), eq(apiUser));

    // Action request execution mocking
    plugins = ImmutableList.of(
        mock(Plugin.class),
        mock(Plugin.class));

    requestExecutor = mock(GetInstalledPluginsRequestExecutor.class);
    doReturn(plugins)
        .when(requestExecutor)
        .execute(eq(request));

    // Output writer mocking
    outputWriter = mock(PluginListOutputWriter.class);
    outputWriterLocator = mock(PluginListOutputWriterLocator.class);
    doReturn(outputWriter)
        .when(outputWriterLocator)
        .locate(eq(OUTPUT_FORMAT));

    // Create the command handler
    commandHandler = new FetchInstalledPluginsCommandHandler(
        apiUserRetriever, requestFactory, requestExecutor, outputWriterLocator);
  }
}
