package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.google.inject.Provider;

/**
 * {@link JenkinsClient.Builder} {@link Provider}.
 *
 * @author Grayson Kuhns
 */
public interface JenkinsClientBuilderProvider extends Provider<JenkinsClient.Builder> {
}
