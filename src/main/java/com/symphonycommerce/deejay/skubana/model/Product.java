package com.symphonycommerce.deejay.skubana.model;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.Entity;
import com.symphonycommerce.deejay.ecommerce.entities.ProductEntity;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Entity, ProductEntity {

  /**
   * GLOBAL REQUIRED.
   */
  @JsonProperty("ID")
  Integer id;

  @JsonProperty(value = "Sku", required = true)
  String sku;

  @JsonProperty(value = "Title", required = true)
  String title;

  @JsonProperty("CreateDateUtc")
  Date createDateUtc;

  @JsonProperty("UpdateDateUtc")
  Date updateDateUtc;

  /**
   * Identifies the Skubana account.
   */
  @JsonProperty("ProfileID")
  Integer profileId;


  /**
   * FULFILLMENT PROPERTIES.
   */

  /**
   * Default unit in US profiles is "Inches". All other locales are "Centimeters".
   */
  @JsonProperty("Height")
  BigDecimal height;

  @JsonProperty("Length")
  BigDecimal length;

  @JsonProperty("Width")
  BigDecimal width;

  /**
   * Default unit in US profiles is "Pounds". All other locales are "Kilograms".
   */
  @JsonProperty("Weight")
  BigDecimal weight;

  @JsonProperty("HarmonizedCode")
  String harmonizedCode;

  @JsonProperty("UPC")
  String upc;

  /**
   * Tax product code for this item (for reseller use, NOT sales tax).
   */
  @JsonProperty("TaxProductCode")
  String taxProductCode;

  @JsonProperty("DCQuantities")
  List<DcQuantity> dcQuantities;


  /**
   * PRICING.
   */


  /**
   * The cost of the product to the seller. This can be the product acquisition cost or the
   * manufacturing cost to produce a single item.
   */
  @JsonProperty("Cost")
  BigDecimal cost;

  @JsonProperty("Margin")
  BigDecimal margin;

  /**
   * The price at which an item sells in normal retail channels.
   */
  @JsonProperty("RetailPrice")
  BigDecimal retailPrice;

  /**
   * The price entered as the beginning, or starting bid, for an ad that is posted to an online
   * marketplace.
   */
  @JsonProperty("StartingPrice")
  BigDecimal startingPrice;

  /**
   * The price entered as the reserve price, or the minimum amount for which an item may sell, for
   * an ad that is posted to an online marketplace.
   */
  @JsonProperty("ReservePrice")
  BigDecimal reservePrice;

  /**
   * The price entered as the fixed purchase price for an ad that is posted to an online
   * marketplace.
   */
  @JsonProperty("BuyItNowPrice")
  BigDecimal buyItNowPrice;


  /**
   * The Second Chance Offer Price field will be used when the Automatic Second Chance Offer feature
   * is enabled in the Sales Settings.
   */
  @JsonProperty("SecondChancePrice")
  BigDecimal secondChancePrice;

  @JsonProperty("SupplierName")
  String supplierName;

  @JsonProperty("SupplierCode")
  String supplierCode;

  @JsonProperty("SupplierPO")
  String supplierPo;

  @JsonProperty("Classification")
  String classification;

  @JsonProperty("BundleType")
  String bundleType;

  /**
   * QUANTITY PROPERTIES.
   */
  @JsonProperty("QuantityUpdateDateUtc")
  Date quantityUpdateDateUtc;

  @JsonProperty("LastSaleDateUtc")
  Date lastSaleDateUtc;

  /**
   * The quantity available to be listed or sold from both seller-managed and externally-managed
   * distribution centers. This number does not include any items that are currently allocated or in
   * a pending status.
   */
  @JsonProperty("TotalAvailableQuantity")
  Integer totalAvailableQuantity;
  /**
   * The allocated quantity from open listings across all marketplaces for this item.
   */
  @JsonProperty("OpenAllocatedQuantity")
  Integer openAllocatedQuantity;


  /**
   * The quantity that has closed as a successful sale, but is still awaiting buyer checkout.
   */
  @JsonProperty("PendingCheckoutQuantity")
  Integer pendingCheckoutQuantity;


  /**
   * The quantity for which the buyer has completed checkout but payment has not been received.
   */
  @JsonProperty("PendingPaymentQuantity")
  Integer pendingPaymentQuantity;


  /**
   * The quantity paid for but pending shipment to the buyer.
   */
  @JsonProperty("PendingShipmentQuantity")
  Integer pendingShipmentQuantity;


  /**
   * The quantity on hand in the warehouse or the quantity in stock and available to be posted to
   * marketplaces. This number is a total of the other columns and includes pending quantity.
   */
  @JsonProperty("TotalQuantity")
  Integer totalQuantity;

  /**
   * The total allocated quantity from open listings across all marketplaces and all pooled accounts
   * for this item.
   */
  @JsonProperty("OpenAllocatedQuantityPooled")
  Integer openAllocatedQuantityPooled;

  /**
   * The total quantity that has closed as a successful sale for this item across all pooled
   * accounts, but are still awaiting buyer checkout.
   */
  @JsonProperty("PendingCheckoutQuantityPooled")
  Integer pendingCheckoutQuantityPooled;

  /**
   * The quantity for which the buyer has completed checkout but payment has not been received for
   * this item across all pooled accounts.
   */
  @JsonProperty("PendingPaymentQuantityPooled")
  Integer pendingPaymentQuantityPooled;

  /**
   * The quantity paid for but pending shipment to the buyer, for this item, across all pooled
   * accounts.
   */
  @JsonProperty("PendingShipmentQuantityPooled")
  Integer pendingShipmentQuantityPooled;

  /**
   * The quantity on hand in the warehouse or the quantity in stock and available to be posted to
   * marketplaces. This number is the total of the other columns and includes pending quantity for
   * this item across all pooled accounts.
   */
  @JsonProperty("TotalQuantityPooled")
  Integer totalQuantityPooled;


  /**
   *  VARIANTS.
   */

  /**
   * A simple boolean to indicate if this is a parent product (one that contains variants) or a leaf
   * variant product (if false).
   */
  @JsonProperty("IsParent")
  boolean isParent;

  /**
   * This value is true if the parent has children.
   */
  @JsonProperty("IsInRelationship")
  boolean isInRelationship;

  /**
   * If this is a child variant, this will be the ID of the parent product that contains all the
   * rest of the variants.
   */
  @JsonProperty("ParentProductID")
  Long parentProductId;


  /**
   * Attribute name used, only available if this is a parent product.
   */
  @JsonProperty("RelationshipName")
  String relationshipName;


  /**
   * MISC STUFF WE DON'T USE.
   */


  @JsonProperty("IsBlocked")
  boolean isBlocked;

  /**
   * If true, will prevent listing and fulfillment from Externally-Managed DCs.
   */
  @JsonProperty("IsExternalQuantityBlocked")
  boolean isExternalQuantityBlocked;

  @JsonProperty("BlockComment")
  String blockComment;

  @JsonProperty("BlockedDateUtc")
  String blockedDateUtc;

  @JsonProperty("ReceivedDateUtc")
  DateTime receivedDateUtc;

  @JsonProperty("ASIN")
  String asin;

  @JsonProperty("Brand")
  String brand;

  @JsonProperty("Condition")
  String condition;

  @JsonProperty("Description")
  String description;

  @JsonProperty("EAN")
  String ean;

  @JsonProperty("FlagDescription")
  String flagDescription;

  @JsonProperty("Flag")
  String flag;

  @JsonProperty("ISBN")
  String isbn;

  @JsonProperty("Manufacturer")
  String manufacturer;

  @JsonProperty("MPN")
  String mpn;

  @JsonProperty("ShortDescription")
  String shortDescription;

  @JsonProperty("Subtitle")
  String subtitle;

  @JsonProperty("WarehouseLocation")
  String warehouseLocation;

  @JsonProperty("Warranty")
  String warranty;

  /**
   * DEPRECATED BY CA.
   */
  @JsonProperty("IsDisplayInStore")
  @Deprecated
  boolean isDisplayInStore;

  @JsonProperty("StoreTitle")
  @Deprecated
  String storeTitle;

  @JsonProperty("StoreDescription")
  @Deprecated
  String storeDescription;

  @JsonProperty("IsAvailableInStore")
  @Deprecated
  boolean isAvailableInStore;

  @JsonProperty("StorePrice")
  @Deprecated
  BigDecimal storePrice;

  public String getIdString() {
    return id.toString();
  }

  public Integer getId() {
    return id;
  }

  public String getSku() {
    return sku;
  }

  public String getTitle() {
    return title;
  }

  public Date getCreateDateUtc() {
    return createDateUtc;
  }

  public Date getUpdateDateUtc() {
    return updateDateUtc;
  }

  public Integer getProfileId() {
    return profileId;
  }

  public BigDecimal getHeight() {
    return height;
  }

  public BigDecimal getLength() {
    return length;
  }

  public BigDecimal getWidth() {
    return width;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public String getHarmonizedCode() {
    return harmonizedCode;
  }

  public String getUpc() {
    return upc;
  }

  public String getTaxProductCode() {
    return taxProductCode;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public BigDecimal getMargin() {
    return margin;
  }

  public BigDecimal getRetailPrice() {
    return retailPrice;
  }

  public BigDecimal getStartingPrice() {
    return startingPrice;
  }

  public BigDecimal getReservePrice() {
    return reservePrice;
  }

  public BigDecimal getBuyItNowPrice() {
    return buyItNowPrice;
  }

  public BigDecimal getSecondChancePrice() {
    return secondChancePrice;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public String getSupplierCode() {
    return supplierCode;
  }

  public String getSupplierPo() {
    return supplierPo;
  }

  public String getClassification() {
    return classification;
  }

  public String getBundleType() {
    return bundleType;
  }

  public Date getQuantityUpdateDateUtc() {
    return quantityUpdateDateUtc;
  }

  public Date getLastSaleDateUtc() {
    return lastSaleDateUtc;
  }

  public Integer getTotalAvailableQuantity() {
    return totalAvailableQuantity;
  }

  public Integer getOpenAllocatedQuantity() {
    return openAllocatedQuantity;
  }

  public Integer getPendingCheckoutQuantity() {
    return pendingCheckoutQuantity;
  }

  public Integer getPendingPaymentQuantity() {
    return pendingPaymentQuantity;
  }

  public Integer getPendingShipmentQuantity() {
    return pendingShipmentQuantity;
  }

  public Integer getTotalQuantity() {
    return totalQuantity;
  }

  public Integer getOpenAllocatedQuantityPooled() {
    return openAllocatedQuantityPooled;
  }

  public Integer getPendingCheckoutQuantityPooled() {
    return pendingCheckoutQuantityPooled;
  }

  public Integer getPendingPaymentQuantityPooled() {
    return pendingPaymentQuantityPooled;
  }

  public Integer getPendingShipmentQuantityPooled() {
    return pendingShipmentQuantityPooled;
  }

  public Integer getTotalQuantityPooled() {
    return totalQuantityPooled;
  }

  public boolean isParent() {
    return isParent;
  }

  public boolean isInRelationship() {
    return isInRelationship;
  }

  public Long getParentProductId() {
    return parentProductId;
  }

  public String getRelationshipName() {
    return relationshipName;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public boolean isExternalQuantityBlocked() {
    return isExternalQuantityBlocked;
  }

  public String getBlockComment() {
    return blockComment;
  }

  public String getBlockedDateUtc() {
    return blockedDateUtc;
  }

  public DateTime getReceivedDateUtc() {
    return receivedDateUtc;
  }

  public String getAsin() {
    return asin;
  }

  public String getBrand() {
    return brand;
  }

  public String getCondition() {
    return condition;
  }

  public String getDescription() {
    return description;
  }

  public String getEan() {
    return ean;
  }

  public String getFlagDescription() {
    return flagDescription;
  }

  public String getFlag() {
    return flag;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getMpn() {
    return mpn;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public String getWarehouseLocation() {
    return warehouseLocation;
  }

  public String getWarranty() {
    return warranty;
  }

  public boolean isDisplayInStore() {
    return isDisplayInStore;
  }

  public String getStoreTitle() {
    return storeTitle;
  }

  public String getStoreDescription() {
    return storeDescription;
  }

  public boolean isAvailableInStore() {
    return isAvailableInStore;
  }

  public BigDecimal getStorePrice() {
    return storePrice;
  }

  public List<DcQuantity> getDcQuantities() {
    return dcQuantities;
  }


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("sku", sku)
        .add("title", title).add("quantities", dcQuantities).toString();
  }
}
