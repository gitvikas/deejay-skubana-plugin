package com.symphonycommerce.deejay.skubana;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.symphonycommerce.deejay.skubana.connector.ConnectionProvider;
import com.symphonycommerce.deejay.ecommerce.ConnectionIntegrationTest;
import com.symphonycommerce.deejay.ecommerce.EcommConnectionTestHelpers;
import com.symphonycommerce.deejay.ecommerce.EcommerceConnection;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;

import com.symphonycommerce.deejay.ecommerce.entities.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SkubanaConnectionTest implements ConnectionIntegrationTest {
  private static final Logger LOG = LoggerFactory.getLogger(
          SkubanaConnectionTest.class.getName());

  EcommerceConnection connection;

  @BeforeMethod
  public void setUp() throws Exception {
    Map<String, String> refreshTokens = new HashMap<>();
    refreshTokens.put("testBrand", "ZVHLQGL0SZ0KjKVgGnf_1TZIReoV5P8tnq-BhuDwTNE");
    refreshTokens.put("botoys", "ECA-_z5oXj4n1jwo_7EIpMrIneFltFrSCaZI-AMfYqk");
    refreshTokens.put("grivitz", "2FxRTntXfz_dQ1MOcA4Ux4trnbAxUz7i0haIu4xJUno");
    ChannelAdvisorLiveConfig liveConfig = new ChannelAdvisorLiveConfig();
    liveConfig.setAppId("0lhvroo4vhajixeb7s70sje1kv3ejkid");
    liveConfig.setSharedSecret("8RMl8GjyYUSLQHG-Nz8ozw");
    liveConfig.setBrandToRefreshToken(refreshTokens);

    ConnectionProvider provider = new ConnectionProvider(liveConfig);
    connection = new ChannelAdvisorConnection(provider);
  }


  @Override
  @Test(enabled = false)
  public void testProductsShouldHaveNecessaryFields() throws JsonProcessingException {
    List<ProductEntity> products = connection.getAllProducts("grivitz");
    System.out.println(products);
    EcommConnectionTestHelpers.assertValidProduct(products.get(0));
  }

  @Test(enabled = false)
  public void testNewOrderShouldHaveValidCustomerInfo() throws Exception {
    List<OrderEntity> orders = getConnection().getNewOrders("grivitz");
    EcommConnectionTestHelpers.assertValidCustomerInfo(orders.get(0));
  }

  @Test(enabled = true)
  @Override
  public void testNewOrderAddressShouldBeValid() throws Exception {
    List<OrderEntity> orders = getConnection().getRecentOrders(getBrand());
    EcommConnectionTestHelpers.assertValidAddress(orders.get(0).getShippingAddress());
  }

  @Override
  public void testShouldBeAbleToAckAnOrder() throws Exception {

  }

  @Override
  public void testShouldBeAbleToUpdateInventory() throws Exception {

  }

  @Override
  public void testShouldBeAbleToCancelAnOrder() throws Exception {

  }

  @Override
  public void testShouldBeAbleToShipOrder() {

  }


  @Override
  public void testNewOrderShouldHaveRequiredFulfillmentFields() throws Exception {

  }

  @Override
  public EcommerceConnection getConnection() {
    return connection;
  }

  @Override
  public String getBrand() {
    return "testBrand";
  }

}
