package com.xellitix.jenkins.plugintool.system;

/**
 * {@link System} proxy.
 *
 * <p>
 *   Shim used to make unit testing easier.
 * </p>
 *
 * @author Grayson Kuhns
 */
public interface SystemProxy {

  /**
   * Gets the value of an environment variable.
   *
   * @param variableName The environment variable name.
   * @return The environment variable value or null if it is not set.
   */
  String getEnv(String variableName);
}
