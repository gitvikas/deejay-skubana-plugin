package com.symphonycommerce.deejay.skubana.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineItem implements OrderEntity.OrderLineItem {
  private Long id;
  private Long profileId;
  private Long orderId;
  private String productId;
  private String siteOrderItemId;
  private String siteListingId;
  private String sku;
  private String title;
  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal taxPrice;
  private BigDecimal shippingPrice;
  private BigDecimal shippingTaxPrice;
  private BigDecimal recyclingFee;
  private String giftMessage;
  private String giftNotes;
  private BigDecimal giftPrice;
  private BigDecimal giftTaxPrice;
  private Boolean isBundle;
  private String itemUrl;
  private List<OrderItemAdjustment> adjustments;

  public OrderLineItem() {
  }

  @Override
  public String getIdString() {
    return getId().toString();
  }

  @JsonProperty("ID")
  public Long getId() {
    return id;
  }

  @JsonProperty("ProfileID")
  public Long getProfileId() {
    return profileId;
  }

  @JsonProperty("OrderID")
  public Long getOrderId() {
    return orderId;
  }

  @JsonProperty("ProductID")
  public String getProductId() {
    return productId;
  }

  @JsonProperty("SiteOrderItemID")
  public String getSiteOrderItemId() {
    return siteOrderItemId;
  }

  @JsonProperty("SiteListingID")
  public String getSiteListingId() {
    return siteListingId;
  }

  @JsonProperty("Sku")
  public String getSku() {
    return sku;
  }

  @JsonProperty("Title")
  public String getTitle() {
    return title;
  }

  @JsonProperty("Quantity")
  public int getQuantity() {
    return quantity;
  }

  @JsonProperty("UnitPrice")
  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  @JsonProperty("TaxPrice")
  public BigDecimal getTaxPrice() {
    return taxPrice;
  }

  @JsonProperty("ShippingPrice")
  public BigDecimal getShippingPrice() {
    return shippingPrice;
  }

  @JsonProperty("ShippingTaxPrice")
  public BigDecimal getShippingTaxPrice() {
    return shippingTaxPrice;
  }

  @JsonProperty("RecyclingFee")
  public BigDecimal getRecyclingFee() {
    return recyclingFee;
  }

  @JsonProperty("GiftMessage")
  public String getGiftMessage() {
    return giftMessage;
  }

  @JsonProperty("GiftNotes")
  public String getGiftNotes() {
    return giftNotes;
  }

  @JsonProperty("GiftPrice")
  public BigDecimal getGiftPrice() {
    return giftPrice;
  }

  @JsonProperty("GiftTaxPrice")
  public BigDecimal getGiftTaxPrice() {
    return giftTaxPrice;
  }

  @JsonProperty("IsBundle")
  public Boolean getBundle() {
    return isBundle;
  }

  @JsonProperty("ItemURL")
  public String getItemUrl() {
    return itemUrl;
  }

  @JsonProperty("Adjustments")
  public List<OrderItemAdjustment> getAdjustments() {
    return adjustments;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProfileId(Long profileId) {
    this.profileId = profileId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public void setSiteOrderItemId(String siteOrderItemId) {
    this.siteOrderItemId = siteOrderItemId;
  }

  public void setSiteListingId(String siteListingId) {
    this.siteListingId = siteListingId;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public void setTaxPrice(BigDecimal taxPrice) {
    this.taxPrice = taxPrice;
  }

  public void setShippingPrice(BigDecimal shippingPrice) {
    this.shippingPrice = shippingPrice;
  }

  public void setShippingTaxPrice(BigDecimal shippingTaxPrice) {
    this.shippingTaxPrice = shippingTaxPrice;
  }

  public void setRecyclingFee(BigDecimal recyclingFee) {
    this.recyclingFee = recyclingFee;
  }

  public void setGiftMessage(String giftMessage) {
    this.giftMessage = giftMessage;
  }

  public void setGiftNotes(String giftNotes) {
    this.giftNotes = giftNotes;
  }

  public void setGiftPrice(BigDecimal giftPrice) {
    this.giftPrice = giftPrice;
  }

  public void setGiftTaxPrice(BigDecimal giftTaxPrice) {
    this.giftTaxPrice = giftTaxPrice;
  }

  public void setBundle(Boolean bundle) {
    isBundle = bundle;
  }

  public void setItemUrl(String itemUrl) {
    this.itemUrl = itemUrl;
  }

  public void setAdjustments(List<OrderItemAdjustment> adjustments) {
    this.adjustments = adjustments;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("quantity", quantity)
        .add("sku", sku)
        .add("title", title)
        .toString();
  }
}
