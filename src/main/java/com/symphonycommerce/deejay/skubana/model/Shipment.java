package com.symphonycommerce.deejay.skubana.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.FulfillmentEntity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Shipment implements FulfillmentEntity {

  enum ShipmentDeliveryStatus {
    UNKNOWN,
    IN_TRANSIT,
    DELIVERED,
    LOST_IN_TRANSIT,
    RETURN_TO_SENDER,
    UNDELIVERABLE,
    FORWARDED,
    VOIDED
  }

  private String trackingNumber;
  private String warehouseId;
  private ShipmentDeliveryStatus deliveryStatus;

  @JsonProperty("shipMethod")
  private ShipmentMethod shipmentMethod;

  // TODO: 20/11/17 Ask Skubana: It appears that you assume that the entire order is shipped in one
  // shipment. Can we do partial shipment where we ship only some of the line items and not all line
  // items. Can't field items for shipment in skubana api
  @Override
  public List<? extends ShipmentItem> getItems() {
    return null;
  }

  @Override
  public String getTrackingNumber() {
    return trackingNumber;
  }

  @Override
  public String getDistributionCenterId() {
    return warehouseId;
  }

  @Override
  public String getShippingCarrier() {
    return shipmentMethod.getShippingCarrier();
  }

  // TODO: 20/11/17 Same as com/symphonycommerce/deejay/skubana/model/Order.java:41
  @Override
  public String getShippingClass() {
    return null;
  }

  @Override
  public FulfillmentStatus getDeliveryStatus() {
    if (trackingNumber == null) {
      return FulfillmentStatus.NoChange;
    }

    switch (deliveryStatus) {
      case IN_TRANSIT:
        return FulfillmentStatus.InTransit;
      case VOIDED:
        return FulfillmentStatus.Canceled;
      case UNDELIVERABLE:
      case LOST_IN_TRANSIT:
      case RETURN_TO_SENDER:
        return FulfillmentStatus.Failed;
      case DELIVERED:
        return FulfillmentStatus.Complete;

        // TODO: 20/11/17 Ask Skubana what's the use case for this cases.
        // UNKNOWN: for FBA orders only; we explicitly set this status when the shipment is
        // "CancelledByFulfiller"
        // FORWARDED: This is not used anywhere, this should be removed from our code
      case FORWARDED:
      case UNKNOWN:
    }

    return null;
  }
}
