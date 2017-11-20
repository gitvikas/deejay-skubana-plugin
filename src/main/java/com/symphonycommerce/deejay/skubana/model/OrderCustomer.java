package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCustomer {
  private String name;
  private String[] emailAddresses;

  public String getName() {
    return name;
  }

  public String[] getEmailAddresses() {
    return emailAddresses;
  }
}
