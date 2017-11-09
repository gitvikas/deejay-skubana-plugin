package com.symphonycommerce.deejay.skubana.connector;

import com.google.common.collect.Maps;

import com.symphonycommerce.deejay.skubana.SkubanaLiveConfig;
import com.symphonycommerce.deejay.skubana.model.Oauth2Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class ConnectionProvider {

  private static final Logger LOG = LoggerFactory.getLogger(ConnectionProvider.class.getName());

  private final SkubanaLiveConfig config;
  private final Client client;
  private final WebTarget rootTarget;
  private final Map<String, OAuthToken> brandToAuthToken;

  /** Creates a connection provider using a configuration that has been loaded from the databse. */
  public ConnectionProvider(SkubanaLiveConfig liveConfig) {
    this.config = liveConfig;

    this.brandToAuthToken = Maps.newHashMap();

    liveConfig
        .getBrandToRefreshToken()
        .forEach(
            (brand, token) -> {
              brandToAuthToken.put(brand, new OAuthToken(token));
            });

    this.client =
        ClientBuilder.newBuilder()
            .property(
                LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT,
                LoggingFeature.Verbosity.PAYLOAD_ANY)
            .property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO")
            .register(new JulFacade())
            .register(new JacksonFeature())
            .build();

    this.rootTarget = client.target("https://api.channeladvisor.com");
  }

  public SkubanaLiveConfig getLiveConfig() {
    return config;
  }

  /**
   * Will return a WebTarget that can be used to make a HTTP request against CA. It will perform the
   * necessary OAUTH steps like keeping track of token expiry and fetching a new token.
   */
  public WebTarget getAuthenticatedClient(String brand) {
    final OAuthToken auth = brandToAuthToken.get(brand);

    if (auth.getExpirationTime().isBeforeNow()) {
      Oauth2Response newAuthTokens = getNewAccessToken(auth);
      DateTime newTime =
          DateTime.now()
              .plusSeconds(newAuthTokens.getExpiresInSeconds())
              .minusSeconds(5); // for some buffer
      auth.setAccessToken(newAuthTokens.getAccessToken(), newTime);
    }

    return rootTarget.queryParam("access_token", auth.getAccessToken());
  }

  /** A lower level method to fetch an oauth token. */
  public Oauth2Response getNewAccessToken(OAuthToken auth) {
    LOG.info("fetching mew access token for {}", auth);

    Client client = ClientBuilder.newClient();

    HttpAuthenticationFeature feature =
        HttpAuthenticationFeature.basic(config.getAppId(), config.getSharedSecret());

    client.register(feature);
    client.register(new JacksonFeature());

    MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
    formData.add("grant_type", "refresh_token");
    formData.add("refresh_token", auth.getRefreshToken());

    return client
        .target("https://api.channeladvisor.com")
        .path("/oauth2/token")
        .request()
        .post(Entity.form(formData), Oauth2Response.class);
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
