package com.xellitix.jenkins.plugintool.updatecenter;

import com.xellitix.jenkins.plugintool.plugin.Plugin;
import java.util.Optional;

/**
 * Jenkins update center.
 *
 * @author Grayson Kuhns
 */
public interface UpdateCenter {

  /**
   * Gets the latest {@link Plugin} release.
   *
   * @param pluginName The {@link Plugin} name.
   * @return The {@link Plugin}.
   * @throws UpdateCenterException If an error occurs.
   */
  Optional<Plugin> getLatestRelease(String pluginName) throws UpdateCenterException;

  /**
   * Gets the latest {@link Plugin} release.
   *
   * @param plugin The old {@link Plugin}.
   * @return The latest {@link Plugin}.
   * @throws UpdateCenterException If an error occurs.
   */
  Optional<Plugin> getLatestRelease(Plugin plugin) throws UpdateCenterException;
}
