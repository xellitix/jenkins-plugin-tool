package com.xellitix.jenkins.plugintool.http;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link ApacheHttpClientProvider} test case.
 *
 * @author Grayson Kuhns
 */
public class ApacheHttpClientProviderTest {

  // Fixtures
  private ApacheHttpClientProvider provider;

  @Test
  public void get__Test() {
    assertThat(provider
        .get())
        .isNotNull()
        .isInstanceOf(HttpClient.class);
  }

  @Before
  public void setUp() {
    provider = new ApacheHttpClientProvider();
  }
}
