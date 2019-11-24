package com.xellitix.jenkins.plugintool.http;

import java.net.URI;
import org.apache.http.client.methods.HttpGet;

/**
 * {@link HttpGet} factory.
 *
 * @author Grayson Kuhns
 */
public interface HttpGetFactory {

  /**
   * Creates an {@link HttpGet}.
   *
   * @param uri The {@link URI}.
   * @return The {@link HttpGet}.
   */
  HttpGet create(URI uri);

  /**
   * Creates an {@link HttpGet}.
   *
   * @param uri The URI.
   * @return The {@link HttpGet}.
   */
  HttpGet create(String uri);
}
