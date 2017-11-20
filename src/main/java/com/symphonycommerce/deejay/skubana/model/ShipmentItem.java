package com.symphonycommerce.deejay.skubana.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.FulfillmentEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipmentItem implements FulfillmentEntity.ShipmentItem {
  // TODO: 20/11/17 Ask Art: Seems like Skubana does not support partial fulfillment. Can't find
  // ShipmentItems
  @Override
  public String getOrderItemId() {
    return null;
  }

  @Override
  public String getProductId() {
    return null;
  }

  @Override
  public int getQuantity() {
    return 0;
  }
}
