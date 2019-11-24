package com.xellitix.jenkins.plugintool.http;

import java.net.URI;
import javax.inject.Singleton;
import org.apache.http.client.methods.HttpGet;

/**
 * Apache {@link HttpGetFactory} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class ApacheHttpGetFactory implements HttpGetFactory {

  /**
   * Creates an {@link HttpGet}.
   *
   * @param uri The {@link URI}.
   * @return The {@link HttpGet}.
   */
  @Override
  public HttpGet create(final URI uri) {
    return new HttpGet(uri);
  }

  /**
   * Creates an {@link HttpGet}.
   *
   * @param uri The URI.
   * @return The {@link HttpGet}.
   */
  @Override
  public HttpGet create(final String uri) {
    return new HttpGet(uri);
  }
}
