package com.symphonycommerce.deejay.channeladvisor.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.FulfillmentEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shipment implements FulfillmentEntity {

  enum FulfillmentType {
    Ship,
    Pickup,
    ShipToStore,
    Courier
  }

  public Shipment() {
  }

  /**
   * Constructor.
   */
  public Shipment(FulfillmentEntity entity) {
    this.trackingNumber = entity.getTrackingNumber();
    this.shippingCarrier = entity.getShippingCarrier();
    this.shippingClass = entity.getShippingClass();
    this.deliveryStatus = entity.getDeliveryStatus();
    this.distributionCenterId = entity.getDistributionCenterId();
    this.items = entity
        .getItems()
        .stream()
        .map(x-> com.symphonycommerce.deejay.channeladvisor.model
            .ShipmentItem.createShipmentItemWithProductId(x.getProductId(),
        x.getQuantity()))
        .collect(Collectors.toList());
  }


  // Available, but unused by us..

  private String sellerFulfillmentId;

  private FulfillmentType fulfilmentType;

  private String distributionCenterId; // optional

  // Typically sent every time

  private Date shippedDateUtc;

  private String trackingNumber;

  private FulfillmentStatus deliveryStatus;

  private String shippingCarrier; // FEDEX, UPS, USPS

  private String shippingClass; //TODO:  Shipping Method conversion needs to happen here: http://ssc.channeladvisor.com/howto/account-shipping-carrier-options

  // Sent only for partial shipments..
  private List<com.symphonycommerce.deejay.channeladvisor.model.ShipmentItem> items;

  @JsonProperty(value = "Items", required = true)
  public List<com.symphonycommerce.deejay.channeladvisor.model.ShipmentItem> getItems() {
    return items;
  }

  @JsonProperty("SellerFulfillmentID")
  public String getSellerFulfillmentId() {
    return sellerFulfillmentId;
  }

  @JsonProperty("FulfilmentType")
  public FulfillmentType getFulfilmentType() {
    return fulfilmentType;
  }

  @JsonProperty("DistributionCenterID")
  public String getDistributionCenterId() {
    return distributionCenterId;
  }

  @JsonProperty("ShippedDateUtc") // optional but encouraged.
  public Date getShippedDateUtc() {
    return shippedDateUtc;
  }

  @JsonProperty(value = "TrackingNumber", required = true)
  public String getTrackingNumber() {
    return trackingNumber;
  }

  @JsonProperty(value = "DeliveryStatus", required = true)
  public FulfillmentStatus getDeliveryStatus() {
    return deliveryStatus;
  }

  @JsonProperty(value = "ShippingCarrier", required = true)
  public String getShippingCarrier() {
    return shippingCarrier;
  }

  @JsonProperty(value = "ShippingClass", required = true)
  public String getShippingClass() {
    return shippingClass;
  }

  public void setSellerFulfillmentId(String sellerFulfillmentId) {
    this.sellerFulfillmentId = sellerFulfillmentId;
  }

  public void setFulfilmentType(FulfillmentType fulfilmentType) {
    this.fulfilmentType = fulfilmentType;
  }

  public void setDistributionCenterId(String distributionCenterId) {
    this.distributionCenterId = distributionCenterId;
  }

  public void setShippedDateUtc(Date shippedDateUtc) {
    this.shippedDateUtc = shippedDateUtc;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  public void setDeliveryStatus(FulfillmentStatus deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public void setShippingCarrier(String shippingCarrier) {
    this.shippingCarrier = shippingCarrier;
  }

  public void setShippingClass(String shippingClass) {
    this.shippingClass = shippingClass;
  }

  public void setItems(List<com.symphonycommerce.deejay.channeladvisor.model.ShipmentItem> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("sellerFulfillmentID", sellerFulfillmentId)
        .add("FulfilmentType", fulfilmentType)
        .add("DistributionCenterID", distributionCenterId)
        .add("shippedDateUtc", shippedDateUtc)
        .add("trackingNumber", trackingNumber)
        .add("DeliveryStatus", deliveryStatus)
        .add("shippingCarrier", shippingCarrier)
        .add("shippingClass", shippingClass)
        .add("items", items)
        .toString();
  }
}
