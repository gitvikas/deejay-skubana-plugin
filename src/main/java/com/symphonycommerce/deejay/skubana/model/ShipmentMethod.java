package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentMethod {
  public int packageTypeId;
  public String shippingCarrier;
  public int shippingProviderId;
  public int shippingServiceId;

  public int getPackageTypeId() {
    return packageTypeId;
  }

  public String getShippingCarrier() {
    return shippingCarrier;
  }

  public int getShippingProviderId() {
    return shippingProviderId;
  }

  public int getShippingServiceId() {
    return shippingServiceId;
  }
}
