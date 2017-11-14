package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.symphonycommerce.deejay.ecommerce.entities.Entity;
import com.symphonycommerce.deejay.ecommerce.entities.ProductEntity;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Entity, ProductEntity {

  private Integer productId;
  private String mpn;
  private String upc;
  private Boolean active;
  private Boolean allowBackorders;
  private String[] attributeGroups;
  private String[] attributes;
  private String brand;
  private Boolean bundle;
  private String[] bundledItems;
  private String[] categories;
  private Date createdDate;
  private Boolean customShippingRule;
  private String description;
  private Boolean hazmat;
  private BigDecimal height;
  private String[] imageUrls;
  private String[] labels;
  private BigDecimal length;
  private Object mapPrice;
  private Date modifiedDate;
  private Integer ounces;
  private Integer parentId;
  private Integer pounds;
  private String[] productStocks;
  private Object shippingCost;
  private Boolean shipsInOwnBox;
  private String type;
  private Object vendorCost;
  private BigDecimal weight;
  private BigDecimal width;

  @JsonProperty("masterSku")
  private String sku;

  @JsonProperty("name")
  private String title;

  public Integer getProductId() {
    return productId;
  }

  public String getMpn() {
    return mpn;
  }

  public String getUpc() {
    return upc;
  }

  public Boolean getActive() {
    return active;
  }

  public Boolean getAllowBackorders() {
    return allowBackorders;
  }

  public String[] getAttributeGroups() {
    return attributeGroups;
  }

  public String[] getAttributes() {
    return attributes;
  }

  public String getBrand() {
    return brand;
  }

  public Boolean getBundle() {
    return bundle;
  }

  public String[] getBundledItems() {
    return bundledItems;
  }

  public String[] getCategories() {
    return categories;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public Boolean getCustomShippingRule() {
    return customShippingRule;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getHazmat() {
    return hazmat;
  }

  public String[] getImageUrls() {
    return imageUrls;
  }

  public String[] getLabels() {
    return labels;
  }

  public Object getMapPrice() {
    return mapPrice;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public Integer getOunces() {
    return ounces;
  }

  public Integer getParentId() {
    return parentId;
  }

  public Integer getPounds() {
    return pounds;
  }

  public String[] getProductStocks() {
    return productStocks;
  }

  public Object getShippingCost() {
    return shippingCost;
  }

  public Boolean getShipsInOwnBox() {
    return shipsInOwnBox;
  }

  public String getType() {
    return type;
  }

  public Object getVendorCost() {
    return vendorCost;
  }

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
    return null;
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
