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
  public DefaultPlugin(
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

  /**
   * Gets the {@link String} representation.
   *
   * @return The {@link String} representation.
   */
  @Override
  public String toString() {
    return String.format("[Plugin NAME: \"%s\", VERSION: \"%s\"]", name, version);
  }

  /**
   * Compare to another {@link Plugin} based on
   *     lexicographical comparison of the {@link Plugin} names.
   *
   * @param other The other {@link Plugin}.
   * @return -1, 0, or 1 if this {@link Plugin} is less than, equal to, or greater than the other
   *     {@link Plugin} based on lexicographical comparison of the {@link Plugin} names.
   */
  @Override
  public int compareTo(final Plugin other) {
    return name.compareTo(other.getName());
  }
}
