package com.xellitix.jenkins.plugintool.api.pluginmanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.xellitix.jenkins.plugintool.plugin.Plugin;
import com.xellitix.jenkins.plugintool.plugin.PluginFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultPluginConverter} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultPluginConverterTest {

  // Constants
  private static final String NAME = "plugin";
  private static final String VERSION = "v1.0.0";

  // Fixtures
  private com.cdancy.jenkins.rest.domain.plugins.Plugin cdancyPlugin;

  private Plugin plugin;
  private PluginFactory pluginFactory;

  private DefaultPluginConverter pluginConverter;

  @Test
  public void convert__Test() {
    assertThat(pluginConverter
        .convert(cdancyPlugin))
        .isNotNull()
        .isEqualTo(plugin);
  }

  @Before
  public void setUp() {
    // Cdancy plugin mocking
    cdancyPlugin = mock(com.cdancy.jenkins.rest.domain.plugins.Plugin.class);
    doReturn(NAME)
        .when(cdancyPlugin)
        .shortName();
    doReturn(VERSION)
        .when(cdancyPlugin)
        .version();

    // XELLITIX plugin mocking
    plugin = mock(Plugin.class);
    pluginFactory = mock(PluginFactory.class);
    doReturn(plugin)
        .when(pluginFactory)
        .create(eq(NAME), eq(VERSION));

    // Create the plugin converter
    pluginConverter = new DefaultPluginConverter(pluginFactory);
  }
}
