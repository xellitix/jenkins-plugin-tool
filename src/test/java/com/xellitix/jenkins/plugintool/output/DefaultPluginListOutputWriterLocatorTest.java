package com.xellitix.jenkins.plugintool.output;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * {@link DefaultPluginListOutputWriterLocator} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultPluginListOutputWriterLocatorTest {

  // Constants
  private static final String EX_MSG_NOT_FOUND =
      "A PluginListOutputWriter could not be found for format JENKINS";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private PluginListOutputWriter writerJson;
  private PluginListOutputWriter writerYaml;
  private DefaultPluginListOutputWriterLocator writerLocator;

  @Test
  public void locateGetsTheCorrectWriter__WhenTheWriterIsRegistered__Test() {
    assertThat(writerLocator
        .locate(PluginListOutputFormat.YAML))
        .isNotNull()
        .isEqualTo(writerYaml);
  }

  @Test
  public void locateThrowsException__WhenWriterIsNotRegistered__Test() {
    // Describe the exception to expect
    thrown.expect(OutputWriteException.class);
    thrown.expectMessage(EX_MSG_NOT_FOUND);

    // Attempt to locate the writer
    writerLocator.locate(PluginListOutputFormat.JENKINS);
  }

  @Before
  public void setUp() {
    // Mock output writers
    writerJson = mock(PluginListOutputWriter.class);
    doReturn(PluginListOutputFormat.JSON)
        .when(writerJson)
        .getFormat();

    writerYaml = mock(PluginListOutputWriter.class);
    doReturn(PluginListOutputFormat.YAML)
        .when(writerYaml)
        .getFormat();

    // Create output writer locator
    writerLocator = new DefaultPluginListOutputWriterLocator(
        ImmutableSet.of(writerJson, writerYaml));
  }
}
