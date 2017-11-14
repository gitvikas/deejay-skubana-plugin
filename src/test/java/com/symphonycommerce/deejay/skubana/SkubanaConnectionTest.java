package com.symphonycommerce.deejay.skubana;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.symphonycommerce.deejay.ecommerce.ConnectionIntegrationTest;
import com.symphonycommerce.deejay.ecommerce.EcommConnectionTestHelpers;
import com.symphonycommerce.deejay.ecommerce.EcommerceConnection;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;
import com.symphonycommerce.deejay.ecommerce.entities.ProductEntity;
import com.symphonycommerce.deejay.skubana.connector.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkubanaConnectionTest implements ConnectionIntegrationTest {

  private static final Logger LOG = LoggerFactory.getLogger(SkubanaConnectionTest.class.getName());
  private String authToken;

  EcommerceConnection connection;

  @BeforeMethod
  public void setUp() throws Exception {
    authToken = "0d996973-62b6-4118-8192-5c0031cd0e08";
    Map<String, String> refreshTokens = new HashMap<>();
    refreshTokens.put(getBrand(), authToken);
    SkubanaLiveConfig liveConfig = new SkubanaLiveConfig();
    //    liveConfig.setAppId("5aUQOevoAUK0x2R4Kk6NNE2Ih5WdwrIZ");
    //    liveConfig.setSharedSecret("TYRgvk61FO2wsKKEeu5NdfZskz8hEme2");
    liveConfig.setBrandToRefreshToken(refreshTokens);

    ConnectionProvider provider = new ConnectionProvider(liveConfig);
    connection = new SkubanaConnection(provider);
  }

  @Override
  public EcommerceConnection getConnection() {
    return connection;
  }

  @Override
  public String getBrand() {
    return "testBrand";
  }

  @Test
  public void testInvalidAuthKeyReturningError() throws Exception {
    getConnection().validateAuthKey("fake:key");
  }

  @Test
  public void testRealAuthKeyShouldNotThrowError() throws Exception {
    getConnection().validateAuthKey(authToken);
  }

  @Test
  @Override
  public void testProductsShouldHaveNecessaryFields() throws JsonProcessingException {
    List<ProductEntity> products = connection.getAllProducts("testBrand");
    System.out.println(products);
    EcommConnectionTestHelpers.assertValidProduct(products.get(0));
  }

  @Test(enabled = false)
  public void testNewOrderShouldHaveValidCustomerInfo() throws Exception {
    List<OrderEntity> orders = getConnection().getNewOrders("grivitz");
    EcommConnectionTestHelpers.assertValidCustomerInfo(orders.get(0));
  }

  @Test(enabled = false)
  @Override
  public void testNewOrderAddressShouldBeValid() throws Exception {
    List<OrderEntity> orders = getConnection().getRecentOrders(getBrand());
    EcommConnectionTestHelpers.assertValidAddress(orders.get(0).getShippingAddress());
  }

  @Override
  public void testShouldBeAbleToAckAnOrder() throws Exception {}

  @Override
  public void testShouldBeAbleToUpdateInventory() throws Exception {}

  @Override
  public void testShouldBeAbleToCancelAnOrder() throws Exception {}

  @Override
  public void testShouldBeAbleToShipOrder() {}

  @Override
  public void testNewOrderShouldHaveRequiredFulfillmentFields() throws Exception {}
}
