package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemAdjustment implements OrderEntity.OrderLineItem.Adjustment {

  // not all of the fields, just the ones we  use
  // see more here:
  // https://developer.channeladvisor.com/working-with-orders/order-entities#OrderEntities-OrderAdjustment

  private String sellerAdjustmentId;
  private Integer quantity;
  private Boolean isRestock;
  private AdjustmentReason reason;
  private AdjustmentType type;

  /** A factory method for making a simple cancellation request with a reason. */
  public static OrderItemAdjustment makeCancellation(
      int qty, AdjustmentReason reason, String sellerAdjustmentId) {
    OrderItemAdjustment adjustment = new OrderItemAdjustment();
    adjustment.quantity = qty;
    adjustment.reason = reason;
    adjustment.sellerAdjustmentId = sellerAdjustmentId;
    return adjustment;
  }

  /** A factory method for maknig a simple cancellation request. */
  public static OrderItemAdjustment makeCancellation(int qty, String sellerAdjustmentId) {
    return makeCancellation(qty, AdjustmentReason.GeneralAdjustment, sellerAdjustmentId);
  }

  @JsonProperty("Quantity")
  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @JsonProperty("isRestock")
  public Boolean isRestock() {
    return isRestock;
  }

  @JsonProperty("Reason")
  public AdjustmentReason getReason() {
    return reason;
  }

  public void setReason(AdjustmentReason reason) {
    this.reason = reason;
  }

  @JsonProperty("Type")
  public AdjustmentType getType() {
    return type;
  }

  public void setType(AdjustmentType type) {
    this.type = type;
  }

  @JsonProperty("SellerAdjustmentID")
  public String getSellerAdjustmentId() {
    return sellerAdjustmentId;
  }

  public void setSellerAdjustmentId(String sellerAdjustmentId) {
    this.sellerAdjustmentId = sellerAdjustmentId;
  }

  public void setRestock(Boolean restock) {
    isRestock = restock;
  }

  enum AdjustmentType {
    Refund,
    Cancellation,
    Dispute
  }

  enum AdjustmentReason {
    GeneralAdjustment,
    ItemNotAvailable,
    CustomerReturnedItem,
    CouldNotShip,
    AlternateItemProvided,
    BuyerCancelled,
    CustomerExchange,
    MerchandiseNotReceived,
    ShippingAddressUndeliverable
  }
}
