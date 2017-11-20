package com.symphonycommerce.deejay.skubana.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineItem implements OrderEntity.OrderLineItem {
  private String listingSku;
  private int quantityOrdered;
  private Integer orderItemId;

  // TODO: 20/11/17 This needs to be verified if listing sku is to be picked or any other field like
  // mastersku, productid
  @Override
  public String getProductId() {
    return listingSku;
  }

  @Override
  public int getQuantity() {
    return quantityOrdered;
  }

  @Override
  public List<? extends Adjustment> getAdjustments() {
    return null;
  }

  @Override
  public String getIdString() {
    return orderItemId.toString();
  }
}
