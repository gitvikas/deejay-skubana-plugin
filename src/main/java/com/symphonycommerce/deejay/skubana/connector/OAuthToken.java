package com.symphonycommerce.deejay.skubana.connector;

import org.joda.time.DateTime;

/** Created by artur on 9/23/16. */
public class OAuthToken {

  DateTime expirationTime;
  String accessToken;
  String refreshToken;

  public OAuthToken(String refreshToken) {
    this.refreshToken = refreshToken;
    this.expirationTime = DateTime.now();
  }

  public DateTime getExpirationTime() {
    return expirationTime;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken, DateTime expiresBy) {
    this.accessToken = accessToken;
    this.expirationTime = expiresBy;
  }

  public String getRefreshToken() {
    return refreshToken;
  }
}
