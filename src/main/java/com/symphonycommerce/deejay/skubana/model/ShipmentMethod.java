package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentMethod {
  public Long packageTypeId;
  public String shippingCarrier;
  public Long shippingProviderId;
  public Long shippingServiceId;

  public Long getPackageTypeId() {
    return packageTypeId;
  }

  public String getShippingCarrier() {
    return shippingCarrier;
  }

  public Long getShippingProviderId() {
    return shippingProviderId;
  }

  public Long getShippingServiceId() {
    return shippingServiceId;
  }
}
