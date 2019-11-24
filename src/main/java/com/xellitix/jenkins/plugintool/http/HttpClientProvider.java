package com.xellitix.jenkins.plugintool.http;

import com.google.inject.Provider;
import org.apache.http.client.HttpClient;

/**
 * {@link HttpClient} {@link Provider}.
 *
 * @author Grayson Kuhns
 */
public interface HttpClientProvider extends Provider<HttpClient> {
}
