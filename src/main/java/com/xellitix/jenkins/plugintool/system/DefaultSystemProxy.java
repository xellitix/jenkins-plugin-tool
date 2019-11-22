package com.xellitix.jenkins.plugintool.system;

/**
 * Default {@link SystemProxy} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultSystemProxy implements SystemProxy {

  /**
   * Gets the value of an environment variable.
   *
   * @param variableName The environment variable name.
   * @return The environment variable value or null if it is not set.
   */
  @Override
  public String getEnv(final String variableName) {
    return System.getenv(variableName);
  }
}
