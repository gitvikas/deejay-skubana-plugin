package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.symphonycommerce.deejay.ecommerce.NameUtils;
import com.symphonycommerce.deejay.ecommerce.entities.Address;
import com.symphonycommerce.deejay.ecommerce.entities.AddressBuilder;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements OrderEntity {

  @JsonProperty(required = true)
  private Long orderId;
  private Map<String, String> salesChannel;
  private OrderCustomer customer;
  private String shipName;
  private String shipCompany;
  private String shipAddress1;
  private String shipAddress2;
  private String shipAddress3;
  private String shipZipCode;
  private String shipCity;
  private String shipState;
  private String shipCountry;
  private String shipPhone;
  private List<Shipment> shipment;

  @JsonProperty("orderItems")
  private List<OrderLineItem> items;

  @Override
  public String getMarketplace() {
    return salesChannel.get("type");
  }

  // TODO: 16/11/17 Ask Skubana: How do I map shipping service information such as “GROUND”
  // TODO: 20/11/17 Ask Art: How important is getShippingClass. Its not there in skuban api's. what
  // default value should I return

  @Override
  public ShippingClass getShippingClass(Optional<String> warehouseId) {
    return ShippingClass.GROUND;
  }

  @Override
  public String getBillingFirstName() {
    String firstName = NameUtils.getFirstName(customer.getName());
    return firstName;
  }

  @Override
  public String getBillingLastName() {
    String lastName = NameUtils.getLastName(customer.getName());
    return lastName;
  }

  @Override
  public String getEmail() {
    String[] emailAddresses = customer.getEmailAddresses();
    return emailAddresses!=null && emailAddresses.length >= 1 ? emailAddresses[0] : null;
  }

  @Override
  public Address getShippingAddress() {
    return new AddressBuilder()
        .setFirstName(NameUtils.getFirstName(shipName))
        .setLastName(NameUtils.getLastName(shipName))
        .setCompany(shipCompany)
        .setAddress1(shipAddress1)
        .setAddress2(shipAddress2 + " " + shipAddress3)
        .setZip(shipZipCode)
        .setCity(shipCity)
        .setState(shipState)
        // TODO: 21/11/17 a change to iso2 from iso3
       .setCountryCode(shipCountry)
        .setPhone(shipPhone)
        .createAddress();
  }

  @Override
  public List<Shipment> getFulfillments() {
    return shipment;
  }

  @Override
  public List<OrderLineItem> getItems() {
    return items;
  }

  @Override
  public String getIdString() {
    return orderId.toString();
  }
}
