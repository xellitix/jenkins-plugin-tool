package com.xellitix.jenkins.plugintool.plugin;

import com.google.inject.assistedinject.Assisted;

import javax.inject.Inject;

/**
 * Default {@link Plugin} implementation.
 *
 * @author Garrett Ewens
 */
public class DefaultPlugin implements Plugin {

  // Properties
  private final String name;
  private final String version;

  /**
   * Constructor.
   *
   * @param name The plugin name.
   * @param version The plugin version.
   */
  @Inject
  DefaultPlugin(
      @Assisted("name") String name,
      @Assisted("version") String version) {

    this.name = name;
    this.version = version;
  }

  /**
   * Gets the name.
   *
   * @return The name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the version.
   *
   * @return The version.
   */
  @Override
  public String getVersion() {
    return version;
  }
}
