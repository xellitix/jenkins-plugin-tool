package com.xellitix.jenkins.plugintool.updatecenter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xellitix.commons.net.compat.java.uri.UriFactory;
import com.xellitix.jenkins.plugintool.http.HttpGetFactory;
import com.xellitix.jenkins.plugintool.plugin.Plugin;
import com.xellitix.jenkins.plugintool.plugin.PluginFactory;
import com.xellitix.jenkins.plugintool.system.SystemProxy;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * Default {@link UpdateCenter} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultUpdateCenter implements UpdateCenter {

  // Constants
  private static final String UPDATE_CENTER_ENDPOINT_ENV_VAR =
      "UPDATE_CENTER_ENDPOINT";
  private static final String DEFAULT_UPDATE_CENTER_ENDPOINT =
      "https://updates.jenkins.io/current/update-center.actual.json";

  // Properties
  private Map<String, Plugin> latestReleases;

  // Dependencies
  private final UriFactory uriFactory;
  private final HttpClient httpClient;
  private final HttpGetFactory httpGetFactory;
  private final ObjectMapper objectMapper;
  private final PluginFactory pluginFactory;
  private final SystemProxy system;

  /**
   * Constructor.
   *
   * @param uriFactory The {@link UriFactory}.
   * @param httpClient The {@link HttpClient}.
   * @param httpGetFactory The {@link HttpGetFactory}.
   * @param objectMapper The {@link ObjectMapper}.
   * @param pluginFactory The {@link PluginFactory}.
   * @param system The {@link SystemProxy}.
   */
  @Inject
  DefaultUpdateCenter(
      final UriFactory uriFactory,
      final HttpClient httpClient,
      final HttpGetFactory httpGetFactory,
      final ObjectMapper objectMapper,
      final PluginFactory pluginFactory,
      final SystemProxy system) {

    this.uriFactory = uriFactory;
    this.httpClient = httpClient;
    this.httpGetFactory = httpGetFactory;
    this.objectMapper = objectMapper;
    this.pluginFactory = pluginFactory;
    this.system = system;
  }

  /**
   * Gets the latest {@link Plugin} release.
   *
   * @param pluginName The {@link Plugin} name.
   * @return The {@link Plugin}.
   * @throws UpdateCenterException If an error occurs.
   */
  @Override
  public Optional<Plugin> getLatestRelease(final String pluginName) throws UpdateCenterException {
    if (latestReleases == null) {
      loadData();
    }

    return Optional.ofNullable(latestReleases.get(pluginName));
  }

  /**
   * Gets the latest {@link Plugin} release.
   *
   * @param plugin The old {@link Plugin}.
   * @return The latest {@link Plugin}.
   * @throws UpdateCenterException If an error occurs.
   */
  @Override
  public Optional<Plugin> getLatestRelease(final Plugin plugin) throws UpdateCenterException {
    return getLatestRelease(plugin.getName());
  }

  private void loadData() {
    // Get update center URI
    final URI updateCenterUri = getUpdateCenterEndpoint();

    // Create the request
    final HttpGet request = httpGetFactory.create(updateCenterUri);

    // Execute the request
    final JsonNode response;
    try {
      response = httpClient.execute(request, httpResponse -> {
        final int status = httpResponse.getStatusLine().getStatusCode();

        // Check the response status
        if (status < 200 || status >= 300) {
          throw new IOException();
        }

        // Get the response entity
        final HttpEntity entity = httpResponse.getEntity();
        if (entity == null) {
          throw new IOException();
        }

        // Parse the response as JSON
        return objectMapper.readTree(entity.getContent());
      });
    } catch (IOException ex) {
      throw new UpdateCenterException(ex);
    }

    // Create the latest releases map
    latestReleases = new HashMap<>();

    // Get the plugins data
    final JsonNode pluginsNode = response.get("plugins");
    for (JsonNode pluginNode : pluginsNode) {
      final String name = pluginNode.get("name").asText();
      final String version = pluginNode.get("version").asText();

      final Plugin plugin = pluginFactory.create(name, version);
      latestReleases.put(name, plugin);
    }
  }

  private URI getUpdateCenterEndpoint() {
    // Get the endpoint as configured by an environment variable
    String endpoint = system.getEnv(UPDATE_CENTER_ENDPOINT_ENV_VAR);

    // Use the default value if the endpoint was not configured
    if (endpoint == null) {
      endpoint = DEFAULT_UPDATE_CENTER_ENDPOINT;
    }

    // Create the URI
    try {
      return uriFactory.create(endpoint);
    } catch (URISyntaxException ex) {
      throw new UpdateCenterException(ex);
    }
  }
}
