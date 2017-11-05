package com.symphonycommerce.deejay.channeladvisor.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.FulfillmentEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipmentItem implements FulfillmentEntity.ShipmentItem {

  public ShipmentItem() {
  }

  private String productId;
  private String sku;
  private String lineItemId; // not used by CA
  private int quantity;

  /**
   * Factory method for creating products with product ID.
   */
  public static ShipmentItem createShipmentItemWithProductId(String productId, int quantity) {
    ShipmentItem si = new ShipmentItem();
    si.productId = productId;
    si.quantity = quantity;
    return si;
  }

  @JsonProperty("Sku")
  public String getSku() {
    return sku;
  }

  @JsonProperty("Quantity")
  public int getQuantity() {
    return quantity;
  }

  @Override
  public String getOrderItemId() {
    return lineItemId;
  }

  @JsonProperty("ProductID")
  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("Sku", sku)
        .add("ProductID", productId)
        .add("Quantity", quantity)
        .toString();
  }
}