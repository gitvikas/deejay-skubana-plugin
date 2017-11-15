package com.symphonycommerce.deejay.skubana.connector;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import com.symphonycommerce.deejay.skubana.SkubanaLiveConfig;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;

public class ConnectionProvider {

  private static final Logger LOG = LoggerFactory.getLogger(ConnectionProvider.class.getName());
  private static final String rootTarget = "https://demo.skubana.com/service/";

  private final SkubanaLiveConfig config;

  /** Creates a connection provider using a configuration that has been loaded from the database. */
  public ConnectionProvider(SkubanaLiveConfig liveConfig) {
    this.config = liveConfig;

  }

  /** Gets a client that can be used for making requests. */
  public WebTarget getAuthenticatedClient(String brand) {
    return getClientByBrand(brand).target(rootTarget);
  }

  public WebTarget getAuthenticatedClientByToken(String authToken) {
    return getClientByToken(authToken).target(rootTarget);
  }

  private Client getClientByBrand(String brand) {
    return clientCache.getUnchecked(brand);
  }

  private LoadingCache<String, Client> clientCache =
      CacheBuilder.newBuilder()
          .maximumSize(20)
          .build(
              new CacheLoader<String, Client>() {
                public Client load(String brand) {
                  final String authToken = config.getBrandToRefreshToken().get(brand);
                  return getClientByToken(authToken);
                }
              });

  private Client getClientByToken(String authToken) {

    return ClientBuilder.newBuilder()
        .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
        .property(
            LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
        .property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO")
        .register(new JulFacade())
        .register(new JacksonFeature())
        .register(
            new ClientRequestFilter() {
              @Override
              public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().add("Authorization", "Bearer " + authToken);
              }
            })
        .build();
  }

  private static class JulFacade extends java.util.logging.Logger {
    JulFacade() {
      super("Jersey", null);
    }

    @Override
    public boolean isLoggable(Level level) {
      return true;
    }

    @Override
    public void warning(String msg) {
      LOG.warn(msg);
    }

    @Override
    public void info(String msg) {
      LOG.info(msg);
    }
  }
}
