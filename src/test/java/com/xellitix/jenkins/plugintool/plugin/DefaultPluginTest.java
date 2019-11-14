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
  private static final String NAME = "name";
  private static final String VERSION = "version";

  // Fixtures
  private DefaultPlugin defaultPlugin;

  @Test
  public void getName__Test() {
    assertThat(defaultPlugin
        .getName())
        .isNotNull()
        .isEqualTo(NAME);
  }

  @Test
  public void getVersion__Test() {
    assertThat(defaultPlugin
        .getVersion())
        .isNotNull()
        .isEqualTo(VERSION);
  }

  @Before
  public void setup() {
    defaultPlugin = new DefaultPlugin("name", "version");
  }
}
