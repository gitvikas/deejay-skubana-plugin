package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.symphonycommerce.deejay.ecommerce.entities.Entity;
import com.symphonycommerce.deejay.ecommerce.entities.ProductEntity;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements ProductEntity {

  @JsonProperty("masterSku")
  private String sku;

  @JsonProperty("name")
  private String title;

  private BigDecimal length;
  private BigDecimal width;
  private BigDecimal height;
  private BigDecimal weight;
  private Map<String, Integer> vendorCost;

  @Override
  public String getSku() {
    return sku;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getSubtitle() {
    return null;
  }

  @Override
  public BigDecimal getCost() {
    Integer number = vendorCost.get("amount");
    return number == 0 ? null : new BigDecimal(number);
  }

  @Override
  public BigDecimal getRetailPrice() {
    return null;
  }

  @Override
  public String getHarmonizedCode() {
    return null;
  }

  @Override
  public String getTaxProductCode() {
    return null;
  }

  @Override
  public BigDecimal getLength() {
    return length;
  }

  @Override
  public BigDecimal getWidth() {
    return width;
  }

  @Override
  public BigDecimal getHeight() {
    return height;
  }

  @Override
  public BigDecimal getWeight() {
    return weight;
  }

  @Override
  public String getIdString() {
    return sku;
  }
}
