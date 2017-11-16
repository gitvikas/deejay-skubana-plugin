package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.symphonycommerce.deejay.ecommerce.NameUtils;
import com.symphonycommerce.deejay.ecommerce.entities.Address;
import com.symphonycommerce.deejay.ecommerce.entities.AddressBuilder;
import com.symphonycommerce.deejay.ecommerce.entities.FulfillmentEntity;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements OrderEntity {

  private Map<String, String> customer;
  private String shipName;
  private String shipCompany;
  private String shipAddress1;
  private String shipAddress2;
  private String shipZipCode;
  private String shipCity;
  private String shipState;
  private String shipCountry;
  private String shipPhone;

  // TODO: 16/11/17 Get in touch with Skubana people to know which API field represents Marketplace
  @Override
  public String getMarketplace() {
    return null;
  }

  @Override
  public ShippingClass getShippingClass(Optional<String> warehouseId) {
    return null;
  }

  @Override
  public String getBillingFirstName() {
    String firstName = NameUtils.getFirstName(customer.get("name"));
    return firstName;
  }

  @Override
  public String getBillingLastName() {
    String lastName = NameUtils.getLastName(customer.get("name"));
    return lastName;
  }

  // TODO: 16/11/17 This needs to be completed
  /* "customer": {
    "companyName": "string",
    "emailAddresses": [
      "string"
    ],
    "name": "string"
  },*/
  @Override
  public String getEmail() {
    String emailAddresses = customer.get("emailAddresses");
    return emailAddresses;
  }

  @Override
  public Address getShippingAddress() {
    return new AddressBuilder()
        .setFirstName(NameUtils.getFirstName(shipName))
        .setLastName(NameUtils.getLastName(shipName))
        .setCompany(shipCompany)
        .setAddress1(shipAddress1)
        .setAddress2(shipAddress2)
        .setZip(shipZipCode)
        .setCity(shipCity)
        .setState(shipState)
        .setCountryCode(shipCountry)
        .setPhone(shipPhone)
        .createAddress();
  }

  @Override
  public List<? extends FulfillmentEntity> getFulfillments() {
    return null;
  }

  @Override
  public List<? extends OrderLineItem> getItems() {
    return null;
  }

  @Override
  public String getIdString() {
    return null;
  }
}
