package com.xellitix.jenkins.plugintool.http;

import javax.inject.Singleton;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Apache {@link HttpClientProvider} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class ApacheHttpClientProvider implements HttpClientProvider {

  /**
   * Gets an {@link HttpClient}.
   *
   * @return The {@link HttpClient}.
   */
  @Override
  public HttpClient get() {
    return HttpClients.createDefault();
  }
}
