package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseQuantity {

  private String distributionCenterId;
  private int quantity;

  public WarehouseQuantity(String distributionCenterId, int quantity) {
    this.distributionCenterId = distributionCenterId;
    this.quantity = quantity;
  }

  @JsonProperty("DistributionCenterID")
  public String getDistributionCenterId() {
    return distributionCenterId;
  }

  @JsonProperty("Quantity")
  public int getQuantity() {
    return quantity;
  }
}
