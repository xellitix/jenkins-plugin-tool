package com.xellitix.jenkins.plugintool.plugin;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * {@link DefaultPlugin} test case.
 *
 * @author Garrett Ewens
 */
public class DefaultPluginTest {

  // Constants
  private static final String NAME = "cool-plugin";
  private static final String VERSION = "1.0.2";
  private static final String EXPECTED_STRING_REPRESENTATION =
      "[Plugin NAME: \"cool-plugin\", VERSION: \"1.0.2\"]";

  // Fixtures
  private DefaultPlugin plugin;
  private Plugin lesserLexographicalPlugin;
  private Plugin greaterLexographicalPlugin;

  @Test
  public void getName__Test() {
    assertThat(plugin
        .getName())
        .isNotNull()
        .isEqualTo(NAME);
  }

  @Test
  public void getVersion__Test() {
    assertThat(plugin
        .getVersion())
        .isNotNull()
        .isEqualTo(VERSION);
  }

  @Test
  public void toString__Test() {
    assertThat(plugin
        .toString())
        .isNotNull()
        .isEqualTo(EXPECTED_STRING_REPRESENTATION);
  }

  @Test
  public void compareTo__ReturnsNegativeInt__WhenPluginNameIsLesserThanOtherPluginName__Test() {
    assertThat(plugin
        .compareTo(greaterLexographicalPlugin))
        .isNegative();
  }

  @Test
  public void compareTo__ReturnsZero__WhenPluginNamesAreEqual__Test() {
    assertThat(plugin
        .compareTo(plugin))
        .isZero();
  }

  @Test
  public void compareTo__ReturnsPositiveInt__WhenPluginNameIsGreaterThanOtherPluginName__Test() {
    assertThat(plugin
        .compareTo(lesserLexographicalPlugin))
        .isPositive();
  }

  @Before
  public void setup() {
    // Mock other plugins for comparison testing
    lesserLexographicalPlugin = mock(Plugin.class);
    doReturn("abc-plugin")
        .when(lesserLexographicalPlugin)
        .getName();

    greaterLexographicalPlugin = mock(Plugin.class);
    doReturn("xyz-plugin")
        .when(greaterLexographicalPlugin)
        .getName();

    // Create the plugin under test
    plugin = new DefaultPlugin(NAME, VERSION);
  }
}
