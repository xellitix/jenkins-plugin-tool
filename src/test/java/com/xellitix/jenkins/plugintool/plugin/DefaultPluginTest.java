package com.xellitix.jenkins.plugintool.plugin;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

  @Before
  public void setup() {
    plugin = new DefaultPlugin(NAME, VERSION);
  }
}
