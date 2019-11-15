package com.xellitix.jenkins.plugintool.output;

import com.xellitix.jenkins.plugintool.plugin.Plugin;

/**
 * {@link Plugin} list output format.
 *
 * @author Grayson Kuhns
 */
public enum PluginListOutputFormat {
  JSON,
  YAML,

  /*
  Official Jenkins plugin format:
  https://github.com/jenkinsci/docker#plugin-version-format

  Ex:
  pluginArtifactId:pluginVersion\n
   */
  JENKINS
}
