package com.symphonycommerce.deejay.channeladvisor.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Oauth2Response {

  @JsonProperty("access_token")
  String accessToken;

  @JsonProperty("token_type")
  String tokenType;

  @JsonProperty("expires_in")
  Integer expiresInSeconds;

  @Override
  public String toString() {
    return "Oauth2Response{"
        + "accessToken='" + accessToken + '\''
        + ", tokenType='" + tokenType + '\''
        + ", expiresInMs=" + expiresInSeconds
        + '}';
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public Integer getExpiresInSeconds() {
    return expiresInSeconds;
  }

  public void setExpiresInSeconds(Integer expiresInMs) {
    this.expiresInSeconds = expiresInMs;
  }
}
