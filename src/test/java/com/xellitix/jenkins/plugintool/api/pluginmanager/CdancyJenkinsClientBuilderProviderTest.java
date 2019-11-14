package com.xellitix.jenkins.plugintool.api.pluginmanager;

import com.cdancy.jenkins.rest.JenkinsClient;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link CdancyJenkinsClientBuilderProvider} test case.
 *
 * @author Grayson Kuhns
 */
public class CdancyJenkinsClientBuilderProviderTest {

  // Fixtures
  private CdancyJenkinsClientBuilderProvider builderProvider;

  @Test
  public void get__CreatesNewJenkinsClientBuilder__Test() {
    assertThat(builderProvider
        .get())
        .isNotNull()
        .isInstanceOf(JenkinsClient.Builder.class);
  }

  @Before
  public void setUp() {
    builderProvider = new CdancyJenkinsClientBuilderProvider();
  }
}
