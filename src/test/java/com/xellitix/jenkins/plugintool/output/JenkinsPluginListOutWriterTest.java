package com.xellitix.jenkins.plugintool.output;

import com.google.common.collect.ImmutableList;
import com.xellitix.jenkins.plugintool.plugin.DefaultPlugin;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link JenkinsPluginListOutputWriter} test case.
 *
 * @author Garrett Ewens
 */
public class JenkinsPluginListOutWriterTest {

  // Constants
  private static final String PLUGIN_ONE_NAME = "workflow";
  private static final String PLUGIN_ONE_VERSION = "1.0.0";

  private static final String PLUGIN_TWO_NAME = "pipeline";
  private static final String PLUGIN_TWO_VERSION = "6.2.4";

  private static final String EXPECTED_OUTPUT =
      "workflow:1.0.0\n" +
          "pipeline:6.2.4\n";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private ByteArrayOutputStream outputStreamData;
  private PrintStream outputStream;
  private List<Plugin> plugins;
  private JenkinsPluginListOutputWriter outputWriter;

  @Test
  public void write__GeneratesCorrectOutput__Test() {
    // Write the output
    outputWriter.write(plugins, outputStream);
    String output = new String(outputStreamData.toByteArray());

    // Validate the output
    assertThat(output).isEqualTo(EXPECTED_OUTPUT);
  }

  @Before
  public void setUp() {
    // Create the output stream
    outputStreamData = new ByteArrayOutputStream();
    outputStream = new PrintStream(outputStreamData);

    // Create the output writer
    outputWriter = new JenkinsPluginListOutputWriter();

    // Create the plugins
    Plugin pluginOne = new DefaultPlugin(PLUGIN_ONE_NAME, PLUGIN_ONE_VERSION);
    Plugin pluginTwo = new DefaultPlugin(PLUGIN_TWO_NAME, PLUGIN_TWO_VERSION);

    plugins = ImmutableList.of(pluginOne, pluginTwo);
  }
}
