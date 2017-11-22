package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineItem implements OrderEntity.OrderLineItem {
  private int quantityOrdered;
  private Long orderItemId;
  private Product product;

  @Override
  public String getProductId() {
    return product.getIdString();
  }

  @Override
  public int getQuantity() {
    return quantityOrdered;
  }

  @Override
  public List<Adjustment> getAdjustments() {
    throw new IllegalStateException("Adjustments are not supported by Skubana");
  }

  @Override
  public String getIdString() {
    return orderItemId.toString();
  }
}
