package com.symphonycommerce.deejay.skubana.model;


import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.Address;
import com.symphonycommerce.deejay.ecommerce.entities.AddressBuilder;
import com.symphonycommerce.deejay.ecommerce.entities.FulfillmentEntity;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Order Model for Channel Advisor. This model is defined in accordance with the following
 * definitions from Channel Advisor: https://developer.channeladvisor.com/working-with-orders/order-entities
 * You can see all possible scenarios that this object and it's supporting methods should and can
 * support by going here:
 * https://developer.channeladvisor.com/files/9044106/9044095/2/1474404951928/REST+API+Order+Scenarios.pdf
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements OrderEntity {

  enum OrderShippingStatus {
    Unshipped,
    Shipped,
    PartiallyShipped,
    PendingShipment,
    Canceled,
    ThirdPartyManaged
  }

  enum OrderPaymentStatus {
    NotYetSubmitted,
    Cleared,
    Submitted,
    Failed,
    Deposited
  }

  @JsonProperty("ID")
  private Long id;
  @JsonProperty("ProfileID")
  private Long profileId;
  @JsonProperty("SiteID")
  private Long siteId;
  @JsonProperty("SiteName")
  private String siteName;
  @JsonProperty("SiteOrderID")
  private String siteOrderId;
  @JsonProperty("SecondarySiteOrderID")
  private String secondarySiteOrderId;
  @JsonProperty("SellerOrderID")
  private String sellerOrderId;
  @JsonProperty("CheckoutSourceID")
  private String checkoutSourceId;
  @JsonProperty("CreatedDateUtc")
  private Date createdDateUtc;
  @JsonProperty("ImportDateUtc")
  private Date importDateUtc;
  @JsonProperty("PublicNotes")
  private String publicNotes;
  @JsonProperty("PrivateNotes")
  private String privateNotes;
  @JsonProperty("SpecialInstructions")
  private String specialInstructions;
  @JsonProperty("TotalPrice")
  private BigDecimal totalPrice;
  @JsonProperty("TotalTaxPrice")
  private BigDecimal totalTaxPrice;
  @JsonProperty("TotalShippingPrice")
  private BigDecimal totalShippingPrice;
  @JsonProperty("TotalShippingTaxPrice")
  private BigDecimal totalShippingTaxPrice;
  @JsonProperty("TotalInsurancePrice")
  private BigDecimal totalInsurancePrice;
  @JsonProperty("TotalGiftOptionPrice")
  private BigDecimal totalGiftOptionPrice;
  @JsonProperty("TotalGiftOptionTaxPrice")
  private BigDecimal totalGiftOptionTaxPrice;
  @JsonProperty("AdditionalCostOrDiscount")
  private BigDecimal additionalCostOrDiscount;
  @JsonProperty("EstimatedShipDateUtc")
  private Date estimatedShipDateUtc;
  @JsonProperty("DeliverByDateUtc")
  private Date deliverByDateUtc;
  @JsonProperty("ResellerID")
  private String resellerId;
  @JsonProperty("FlagDescription")
  private String flagDescription;
  @JsonProperty("FlagID")
  private String flagId;
  @JsonProperty("OrderTags")
  private String orderTags;
  @JsonProperty("DistributionCenterTypeRollup")
  private String distributionCenterTypeRollup;
  @JsonProperty("CheckoutStatus")
  private String checkoutStatus;
  @JsonProperty("PaymentStatus")
  private OrderPaymentStatus paymentStatus;
  @JsonProperty("ShippingStatus")
  private OrderShippingStatus shippingStatus;
  @JsonProperty("CheckoutDateUtc")
  private Date checkoutDateUtc;
  @JsonProperty("PaymentDateUtc")
  private Date paymentDateUtc;
  @JsonProperty("ShippingDateUtc")
  private Date shippingDateUtc;
  @JsonProperty("BuyerUserId")
  private String buyerUserId;
  @JsonProperty("BuyerEmailAddress")
  private String buyerEmailAddress;
  @JsonProperty("BuyerEmailOptIn")
  private Boolean buyerEmailOptIn;
  @JsonProperty("OrderTaxType")
  private String orderTaxType;
  @JsonProperty("ShippingTaxType")
  private String shippingTaxType;
  @JsonProperty("GiftOptionsTaxType")
  private String giftOptionsTaxType;
  @JsonProperty("PaymentMethod")
  private String paymentMethod;
  @JsonProperty("PaymentTransactionID")
  private String paymentTransactionId;
  @JsonProperty("PaymentPaypalAccountID")
  private String paymentPaypalAccountId;
  @JsonProperty("PaymentCreditCardLast4")
  private String paymentCreditCardLast4;
  @JsonProperty("PaymentMerchantReferenceNumber")
  private String paymentMerchantReferenceNumber;
  @JsonProperty("ShippingTitle")
  private String shippingTitle;
  @JsonProperty("ShippingFirstName")
  private String shippingFirstName;
  @JsonProperty("ShippingLastName")
  private String shippingLastName;
  @JsonProperty("ShippingSuffix")
  private String shippingSuffix;
  @JsonProperty("ShippingCompanyName")
  private String shippingCompanyName;
  @JsonProperty("ShippingCompanyJobTitle")
  private String shippingCompanyJobTitle;
  @JsonProperty("ShippingDaytimePhone")
  private String shippingDaytimePhone;
  @JsonProperty("ShippingEveningPhone")
  private String shippingEveningPhone;
  @JsonProperty("ShippingAddressLine1")
  private String shippingAddressLine1;
  @JsonProperty("ShippingAddressLine2")
  private String shippingAddressLine2;
  @JsonProperty("ShippingCity")
  private String shippingCity;
  @JsonProperty("ShippingStateOrProvince")
  private String shippingStateOrProvince;
  @JsonProperty("ShippingPostalCode")
  private String shippingPostalCode;
  @JsonProperty("ShippingCountry")
  private String shippingCountry;
  @JsonProperty("BillingTitle")
  private String billingTitle;
  @JsonProperty("BillingFirstName")
  private String billingFirstName;
  @JsonProperty("BillingLastName")
  private String billingLastName;
  @JsonProperty("BillingSuffix")
  private String billingSuffix;
  @JsonProperty("BillingCompanyName")
  private String billingCompanyName;
  @JsonProperty("BillingCompanyJobTitle")
  private String billingCompanyJobTitle;
  @JsonProperty("BillingDaytimePhone")
  private String billingDaytimePhone;
  @JsonProperty("BillingEveningPhone")
  private String billingEveningPhone;
  @JsonProperty("BillingAddressLine1")
  private String billingAddressLine1;
  @JsonProperty("BillingAddressLine2")
  private String billingAddressLine2;
  @JsonProperty("BillingCity")
  private String billingCity;
  @JsonProperty("BillingStateOrProvince")
  private String billingStateOrProvince;
  @JsonProperty("BillingPostalCode")
  private String billingPostalCode;
  @JsonProperty("BillingCountry")
  private String billingCountry;
  @JsonProperty("PromotionCode")
  private String promotionCode;
  @JsonProperty("PromotionAmount")
  private BigDecimal promotionAmount;
  @JsonProperty("Items")
  private List<com.symphonycommerce.deejay.skubana.model.OrderLineItem> items;
  @JsonProperty("Fulfillments")
  private List<Shipment> fulfillments;

  public Long getId() {
    return id;
  }

  @Override
  public String getIdString() {
    return id == null ? null : id.toString();
  }

  public Long getProfileId() {
    return profileId;
  }

  public Long getSiteId() {
    return siteId;
  }

  public String getSiteName() {
    return siteName;
  }

  public String getSiteOrderId() {
    return siteOrderId;
  }

  public String getSecondarySiteOrderId() {
    return secondarySiteOrderId;
  }

  public String getSellerOrderId() {
    return sellerOrderId;
  }

  public String getCheckoutSourceId() {
    return checkoutSourceId;
  }

  public Date getCreatedDateUtc() {
    return createdDateUtc;
  }

  public Date getImportDateUtc() {
    return importDateUtc;
  }

  public String getPublicNotes() {
    return publicNotes;
  }

  public String getPrivateNotes() {
    return privateNotes;
  }

  public String getSpecialInstructions() {
    return specialInstructions;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public BigDecimal getTotalTaxPrice() {
    return totalTaxPrice;
  }

  public BigDecimal getTotalShippingPrice() {
    return totalShippingPrice;
  }

  public BigDecimal getTotalShippingTaxPrice() {
    return totalShippingTaxPrice;
  }

  public BigDecimal getTotalInsurancePrice() {
    return totalInsurancePrice;
  }

  public BigDecimal getTotalGiftOptionPrice() {
    return totalGiftOptionPrice;
  }

  public BigDecimal getTotalGiftOptionTaxPrice() {
    return totalGiftOptionTaxPrice;
  }

  public BigDecimal getAdditionalCostOrDiscount() {
    return additionalCostOrDiscount;
  }

  public Date getEstimatedShipDateUtc() {
    return estimatedShipDateUtc;
  }

  public Date getDeliverByDateUtc() {
    return deliverByDateUtc;
  }

  public String getResellerId() {
    return resellerId;
  }

  public String getFlagDescription() {
    return flagDescription;
  }

  public String getOrderTags() {
    return orderTags;
  }

  public String getDistributionCenterTypeRollup() {
    return distributionCenterTypeRollup;
  }

  public String getCheckoutStatus() {
    return checkoutStatus;
  }

  public OrderPaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public OrderShippingStatus getShippingStatus() {
    return shippingStatus;
  }

  public Date getCheckoutDateUtc() {
    return checkoutDateUtc;
  }

  public Date getPaymentDateUtc() {
    return paymentDateUtc;
  }

  public Date getShippingDateUtc() {
    return shippingDateUtc;
  }

  public String getBuyerUserId() {
    return buyerUserId;
  }

  public String getBuyerEmailAddress() {
    return buyerEmailAddress;
  }

  public Boolean getBuyerEmailOptIn() {
    return buyerEmailOptIn;
  }

  public String getOrderTaxType() {
    return orderTaxType;
  }

  public String getShippingTaxType() {
    return shippingTaxType;
  }

  public String getGiftOptionsTaxType() {
    return giftOptionsTaxType;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public String getPaymentTransactionId() {
    return paymentTransactionId;
  }


  public String getPaymentCreditCardLast4() {
    return paymentCreditCardLast4;
  }

  public String getPaymentMerchantReferenceNumber() {
    return paymentMerchantReferenceNumber;
  }

  public String getShippingTitle() {
    return shippingTitle;
  }

  public String getShippingFirstName() {
    return shippingFirstName;
  }

  public String getShippingLastName() {
    return shippingLastName;
  }

  public String getShippingSuffix() {
    return shippingSuffix;
  }

  public String getShippingCompanyName() {
    return shippingCompanyName;
  }

  public String getShippingCompanyJobTitle() {
    return shippingCompanyJobTitle;
  }

  public String getShippingDaytimePhone() {
    return shippingDaytimePhone;
  }

  public String getShippingEveningPhone() {
    return shippingEveningPhone;
  }

  public String getShippingAddressLine1() {
    return shippingAddressLine1;
  }

  public String getShippingAddressLine2() {
    return shippingAddressLine2;
  }

  public String getShippingCity() {
    return shippingCity;
  }

  public String getShippingStateOrProvince() {
    return shippingStateOrProvince;
  }

  public String getShippingPostalCode() {
    return shippingPostalCode;
  }

  public String getShippingCountry() {
    return shippingCountry;
  }

  public String getBillingTitle() {
    return billingTitle;
  }

  public String getBillingFirstName() {
    return billingFirstName;
  }

  public String getBillingLastName() {
    return billingLastName;
  }

  @Override
  public String getEmail() {
    return this.buyerEmailAddress;
  }

  @Override
  public Address getShippingAddress() {
    return new AddressBuilder()
        .setFirstName(shippingFirstName)
        .setLastName(shippingLastName)
        .setCompany(shippingCompanyName)
        .setAddress1(shippingAddressLine1)
        .setAddress2(shippingAddressLine2)
        .setCity(shippingCity)
        .setState(shippingStateOrProvince)
        .setZip(shippingPostalCode)
        .setCountryCode(shippingCountry)
        .setPhone(shippingDaytimePhone)
        .createAddress();
  }

  public String getBillingSuffix() {
    return billingSuffix;
  }

  public String getBillingCompanyName() {
    return billingCompanyName;
  }

  public String getBillingCompanyJobTitle() {
    return billingCompanyJobTitle;
  }

  public String getBillingDaytimePhone() {
    return billingDaytimePhone;
  }

  public String getBillingEveningPhone() {
    return billingEveningPhone;
  }

  public String getBillingAddressLine1() {
    return billingAddressLine1;
  }

  public String getBillingAddressLine2() {
    return billingAddressLine2;
  }

  public String getBillingCity() {
    return billingCity;
  }

  public String getBillingStateOrProvince() {
    return billingStateOrProvince;
  }

  public String getBillingPostalCode() {
    return billingPostalCode;
  }

  public String getBillingCountry() {
    return billingCountry;
  }

  public String getPromotionCode() {
    return promotionCode;
  }

  public List<com.symphonycommerce.deejay.skubana.model.OrderLineItem> getItems() {
    return items;
  }

  public List<Shipment> getFulfillments() {
    return fulfillments;
  }

  public String getMarketplace() {
    return this.getSiteName();
  }

  @Override
  public ShippingClass getShippingClass(Optional<String> warehouseId) {
    FulfillmentEntity shipment = getFulfillments().stream().filter(s -> warehouseId.get()
        .equalsIgnoreCase(
        s.getDistributionCenterId())).findFirst().get();

    //see http://ssc.channeladvisor.com/howto/marketplaces-shipping-carrier-class-code-table

    // This can happen for some orders where the user doesn't care what the shipping class is
    // in that case we'll just go for the slow and cheap "Ground"
    if (shipment.getShippingClass() == null) {
      return ShippingClass.GROUND;
    }

    switch (shipment.getShippingClass()) {
      case "Ground":
      case "Standard":
      case "Value":
      case "Standard Shipping (5-7 business days)":
      case "Priority Mail":

      case "Expedited Shipping (3-5 business days)":
      case "Expedited":
      case "Express":
        return ShippingClass.GROUND;
      case "SecondDay":
      case "Two-Day Shipping":
      case "Two Day":
      case "Item2ndDayAir":
        return ShippingClass.TWO_DAY;
      case "Priority":
      case "Overnight":
      case "NextDay":
      case "One Day":
      case "1Day":
      case "One-Day Shipping":
        return ShippingClass.ONE_DAY;
      case "Freight":
        return ShippingClass.FREIGHT;
      default:
        return ShippingClass.UNKNOWN;
    }
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("ID", id)
        .add("profileID", profileId)
        .add("siteID", siteId)
        .add("siteName", siteName)
        .add("siteOrderID", siteOrderId)
        .toString();
  }
}
