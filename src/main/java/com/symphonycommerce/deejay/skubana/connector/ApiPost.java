package com.symphonycommerce.deejay.skubana.connector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiPost<T> {
  private T value;

  public ApiPost(T value) {
    this.value = value;
  }

  @JsonProperty("Value")
  public T getValue() {
    return value;
  }
}
