package com.xellitix.jenkins.plugintool.cli.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link FetchInstalledPluginsCommandHandler} test case.
 *
 * @author Grayson Kuhns
 */
public class FetchInstalledPluginsCommandHandlerTest {

  // Fixtures
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
    handler = new FetchInstalledPluginsCommandHandler();
  }
}
