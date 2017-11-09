package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PendingShipment {
  @JsonProperty("ShippingStatus")
  private Order.OrderShippingStatus shippingStatus = Order.OrderShippingStatus.PendingShipment;
}
