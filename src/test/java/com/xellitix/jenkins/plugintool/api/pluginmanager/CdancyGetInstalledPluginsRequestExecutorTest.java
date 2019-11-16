package com.xellitix.jenkins.plugintool.api.pluginmanager;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import com.cdancy.jenkins.rest.JenkinsApi;
import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.plugins.Plugins;
import com.cdancy.jenkins.rest.features.PluginManagerApi;
import com.google.common.collect.ImmutableList;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * {@link CdancyGetInstalledPluginsRequestExecutor} test case.
 *
 * @author Grayson Kuhns
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JenkinsClient.class)
public class CdancyGetInstalledPluginsRequestExecutorTest {

  // Constants
  private GetInstalledPluginsRequest request;

  private com.cdancy.jenkins.rest.domain.plugins.Plugin cdancyPluginOne;
  private com.cdancy.jenkins.rest.domain.plugins.Plugin cdancyPluginTwo;
  private Plugin pluginOne;
  private Plugin pluginTwo;
  private PluginConverter pluginConverter;

  private JenkinsClient client;
  private JenkinsClientFactory clientFactory;

  private CdancyGetInstalledPluginsRequestExecutor requestExecutor;

  @Test
  public void execute__Test() {
    assertThat(requestExecutor
        .execute(request))
        .isNotNull()
        .hasSize(2)
        .contains(pluginOne)
        .contains(pluginTwo);
  }

  @Before
  public void setUp() {
    // Request mocking
    request = mock(GetInstalledPluginsRequest.class);

    // Plugin mocking
    cdancyPluginOne = mock(com.cdancy.jenkins.rest.domain.plugins.Plugin.class);
    cdancyPluginTwo = mock(com.cdancy.jenkins.rest.domain.plugins.Plugin.class);
    pluginOne = mock(Plugin.class);
    pluginTwo = mock(Plugin.class);

    pluginConverter = mock(PluginConverter.class);
    doReturn(pluginOne)
        .when(pluginConverter)
        .convert(eq(cdancyPluginOne));
    doReturn(pluginTwo)
        .when(pluginConverter)
        .convert(eq(cdancyPluginTwo));

    // API call mocking
    Plugins plugins = mock(Plugins.class);
    doReturn(ImmutableList.of(cdancyPluginOne, cdancyPluginTwo))
        .when(plugins)
        .plugins();

    PluginManagerApi pluginManagerApi = mock(PluginManagerApi.class);
    doReturn(plugins)
        .when(pluginManagerApi)
        .plugins(anyInt(), eq(null));

    JenkinsApi jenkinsApi = mock(JenkinsApi.class);
    doReturn(pluginManagerApi)
        .when(jenkinsApi)
        .pluginManagerApi();

    // Client mocking
    client = mock(JenkinsClient.class);
    doReturn(jenkinsApi)
        .when(client)
        .api();

    clientFactory = mock(JenkinsClientFactory.class);
    doReturn(client)
        .when(clientFactory)
        .create(eq(request));

    // Create the request executor
    requestExecutor = new CdancyGetInstalledPluginsRequestExecutor(clientFactory, pluginConverter);
  }
}
