package com.symphonycommerce.deejay.channeladvisor.model;

import com.google.common.collect.ImmutableList;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

// An object that is used to update inventory available at a particular distribution center
// within Channel Advisor.
// see: https://developer.channeladvisor
// .com/working-with-products/quantity/update-quantity-choose-update-type-single-dc-update
public class UpdateQuantity {


  public enum QuantityUpdateType {
    Absolute,
    Relative,
    Available,
    InStock,
    UnShipped
  }

  public UpdateQuantity() {
  }

  public UpdateQuantity(QuantityUpdateType updateType, List<WarehouseQuantity> updates) {
    this.updateType = updateType;
    this.updates = updates;
  }

  /**
   * Make an inventory update.
   */
  public static UpdateQuantity makeInventoryUpdate(Optional<String> distributionCenterId, int
      availableQuantity) {
    UpdateQuantity uq = new UpdateQuantity();
    uq.setUpdateType(QuantityUpdateType.InStock);
    uq.updates = ImmutableList.of(new WarehouseQuantity(distributionCenterId.orElse(null),
                                                        availableQuantity));
    return uq;
  }

  private QuantityUpdateType updateType;
  private List<WarehouseQuantity> updates;


  @JsonProperty("UpdateType")
  public QuantityUpdateType getUpdateType() {
    return updateType;
  }

  @JsonProperty("Updates")
  public List<WarehouseQuantity> getUpdates() {
    return updates;
  }

  public void setUpdateType(QuantityUpdateType updateType) {
    this.updateType = updateType;
  }

  public void setUpdates(List<WarehouseQuantity> updates) {
    this.updates = updates;
  }
}
