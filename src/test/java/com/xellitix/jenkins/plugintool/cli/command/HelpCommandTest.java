package com.xellitix.jenkins.plugintool.cli.command;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link HelpCommand} test case.
 *
 * @author Garrett Ewens
 */
public class HelpCommandTest {

  // Constants
  private static final String NAME = "help";

  // Fixtures
  private HelpCommand command;

  @Test
  public void getName__Test() {
    assertThat(command
        .getName())
        .isNotNull()
        .isEqualTo(NAME);
  }

  @Before
  public void setup() {
    // Create command
    command = new HelpCommand();
  }
}
