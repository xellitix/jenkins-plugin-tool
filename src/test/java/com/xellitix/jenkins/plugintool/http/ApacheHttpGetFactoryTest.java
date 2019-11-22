package com.xellitix.jenkins.plugintool.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.apache.http.client.methods.HttpGet;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link ApacheHttpGetFactory} test case.
 *
 * @author Grayson Kuhns
 */
public class ApacheHttpGetFactoryTest {

  // Constants
  private static final String ENDPOINT = "https://jenkins.local";

  // Fixtures
  private URI uri;
  private ApacheHttpGetFactory getFactory;

  @Test
  public void create__WithUri__Test() {
    assertThat(getFactory
        .create(uri))
        .isNotNull()
        .isInstanceOf(HttpGet.class);
  }

  @Test
  public void create__WithString__Test() {
    assertThat(getFactory
        .create(ENDPOINT))
        .isNotNull()
        .isInstanceOf(HttpGet.class);
  }

  @Before
  public void setUp() throws Exception {
    // Create the ENDPOINT
    uri = new URI(ENDPOINT);

    // Create the HttpGet factory
    getFactory = new ApacheHttpGetFactory();
  }
}
