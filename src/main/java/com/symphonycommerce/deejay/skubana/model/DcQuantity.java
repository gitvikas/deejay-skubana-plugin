package com.symphonycommerce.deejay.skubana.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DcQuantity {
  private Integer distributionCenterId;
  private Integer availableQuantity;

  @JsonProperty("DistributionCenterID")
  public Integer getDistributionCenterId() {
    return distributionCenterId;
  }

  public void setDistributionCenterId(Integer distributionCenterId) {
    this.distributionCenterId = distributionCenterId;
  }

  @JsonProperty("AvailableQuantity")
  public Integer getAvailableQuantity() {
    return availableQuantity;
  }

  public void setAvailableQuantity(Integer availableQuantity) {
    this.availableQuantity = availableQuantity;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("distributionCenterId", distributionCenterId)
        .add("availableQuantity", availableQuantity)
        .toString();
  }
}
