package com.xellitix.jenkins.plugintool.cli;

import static org.assertj.core.api.Assertions.assertThat;

import com.beust.jcommander.JCommander;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link JcommanderBuilderProvider} test case.
 *
 * @author Grayson Kuhns
 */
public class JcommanderBuilderProviderTest {

  // Fixtures
  private JcommanderBuilderProvider builderProvider;

  @Test
  public void get__CreatesNewJcommanderBuilder__Test() {
    assertThat(builderProvider
        .get())
        .isNotNull()
        .isInstanceOf(JCommander.Builder.class);
  }

  @Before
  public void setUp() {
    builderProvider = new JcommanderBuilderProvider();
  }
}
