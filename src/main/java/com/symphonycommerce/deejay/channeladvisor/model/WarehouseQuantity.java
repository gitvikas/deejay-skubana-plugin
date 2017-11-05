package com.symphonycommerce.deejay.channeladvisor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseQuantity {

  public WarehouseQuantity(String distributionCenterId, int quantity) {
    this.distributionCenterId = distributionCenterId;
    this.quantity = quantity;
  }

  private String distributionCenterId;
  private int quantity;

  @JsonProperty("DistributionCenterID")
  public String getDistributionCenterId() {
    return distributionCenterId;
  }

  @JsonProperty("Quantity")
  public int getQuantity() {
    return quantity;
  }
}
