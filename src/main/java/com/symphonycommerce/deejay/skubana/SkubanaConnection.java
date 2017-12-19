package com.symphonycommerce.deejay.skubana;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import com.symphonycommerce.deejay.config.ConfigurableConnection;
import com.symphonycommerce.deejay.ecommerce.ApiListWrapper;
import com.symphonycommerce.deejay.ecommerce.EcommerceConnection;
import com.symphonycommerce.deejay.ecommerce.entities.FulfillmentEntity;
import com.symphonycommerce.deejay.ecommerce.entities.OrderEntity;
import com.symphonycommerce.deejay.ecommerce.entities.ProductEntity;
import com.symphonycommerce.deejay.ecommerce.exceptions.IllegalConfigurationException;
import com.symphonycommerce.deejay.skubana.connector.ApiPost;
import com.symphonycommerce.deejay.skubana.connector.ConnectionProvider;
import com.symphonycommerce.deejay.skubana.connector.SkubanaApiListWrapper;
import com.symphonycommerce.deejay.skubana.model.DistributionCenter;
import com.symphonycommerce.deejay.skubana.model.Order;
import com.symphonycommerce.deejay.skubana.model.Product;
import com.symphonycommerce.deejay.skubana.model.Shipment;
import com.symphonycommerce.deejay.skubana.model.UpdateQuantity;
import com.symphonycommerce.deejay.store.DeejayLiveConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class SkubanaConnection implements ConfigurableConnection, EcommerceConnection {

  private static final Logger LOG = LoggerFactory.getLogger(SkubanaConnection.class.getName());

  public static final String DISTRIBUTION_CENTER_ID = "SYMPHONY";

  /**
   * The distribution center IDs are unique to an account and therefore we need to keep track of
   * each one. They are also difficult to extract unless you are technical, which is why I decided
   * to grab it using an API. This keeps the setup for a new client to a minimum mod in the DB
   * rather than extensive configuration changes.
   */
  private LoadingCache<String, Integer> distributionCenterIds =
      CacheBuilder.newBuilder()
          .maximumSize(20)
          .build(
              new CacheLoader<String, Integer>() {
                public Integer load(String key) {
                  DistributionCenter dc = findSymphonyDc(key);
                  LOG.info("SkubanaConnection - get DC ID for {}: {}", key, dc);
                  return dc.getId();
                }
              });

  private final ConnectionProvider provider;

  public SkubanaConnection(ConnectionProvider provider) {
    this.provider = provider;
  }

  @Override
  public boolean validateAuthKey(String authKey) {

    try {
      provider
          .getAuthenticatedClientByToken(authKey)
          .path("/v1/products")
          .request()
          .get(new GenericType<List<Product>>() {});
      return true;
    } catch (WebApplicationException ex) {
      LOG.error("verifyAuth", ex);
      return false;
    }
  }

  @Override
  public List<ProductEntity> getAllProducts(String brand)
      throws ProcessingException, WebApplicationException {
    return (List<ProductEntity>) collectPages(this::getPagedProducts, brand);
  }

  /** DISTRIBUTION CENTERS. */
  @Override
  public Optional<String> getCachedSymphonyWarehouseId(String brand) {
    return Optional.of(distributionCenterIds.getUnchecked(brand).toString());
  }

  DistributionCenter findSymphonyDc(String brand) {
    return provider
        .getAuthenticatedClient(brand)
        .path("/v1/DistributionCenters")
        .request()
        .get(new GenericType<ApiListWrapper<DistributionCenter>>() {})
        .getValue()
        .stream()
        .filter(dc -> DISTRIBUTION_CENTER_ID.equalsIgnoreCase(dc.getCode()))
        .findFirst()
        .orElseThrow(IllegalConfigurationException::new);
  }

  /** ===== PRODUCTS. */
  @Override
  public ApiListWrapper<? extends ProductEntity> getPagedProducts(String brand, Integer page) {
    // Integer dcId = distributionCenterIds.getUnchecked(brand);
    List<Product> products =
        provider
            .getAuthenticatedClient(brand)
            .path("/v1/products")
            .queryParam("page", page)
            .queryParam("limit", SkubanaApiListWrapper.PAGE_LIMIT)
            .request()
            .get(new GenericType<List<Product>>() {});
    SkubanaApiListWrapper skubanaApiListWrapper = new SkubanaApiListWrapper();
    skubanaApiListWrapper.setValue(products);
    return skubanaApiListWrapper;
  }

  /** ====== ORDERS. */

  /** Grabs all the orders that have been recently created for a given brand within CA. */
  @Override
  public List<OrderEntity> getNewOrders(String brand) {
    return (List<OrderEntity>) collectPages(this::getPagedNewOrders, brand);
  }

  public ApiListWrapper<? extends OrderEntity> getPagedNewOrders(String brand, Integer page) {
    Integer dcId = distributionCenterIds.getUnchecked(brand);
    Preconditions.checkNotNull(dcId);

    ApiListWrapper<Order> result =
        provider
            .getAuthenticatedClient(brand)
            .path("/v1/orders")
//            .queryParam("$expand", "Items($expand=Adjustments),Fulfillments($expand=Items)")
//            .queryParam("exported", false)
//            .queryParam("$skip", page)
//            .queryParam(
//                "$filter",
//                "PaymentStatus eq 'Cleared' and Fulfillments/Any (c: "
//                    + "c/DistributionCenterID eq "
//                    + dcId
//                    + ") and ShippingStatus ne 'Shipped'"
//                    + " and ShippingStatus ne 'Canceled'")
            .request()
            .get(new GenericType<ApiListWrapper<Order>>() {});

    return result;
  }

  /** Get the orders that have been awknowledged by us (therefore created), but not yet shipped. */
  @Override
  public List<OrderEntity> getRecentOrders(String brand) {
    return (List<OrderEntity>) collectPages(this::getPagedRecentOrders, brand);
  }

  /** Get the recent orders for a given page. */
  @Override
  public ApiListWrapper<? extends OrderEntity> getPagedRecentOrders(String brand, Integer page) {
    return provider
        .getAuthenticatedClient(brand)
        .path("/v1/orders")
        .queryParam("$expand", "Items($expand=Adjustments),Fulfillments($expand=Items)")
        .queryParam("$filter", "ShippingStatus ne 'Shipped' and ShippingStatus ne " + "'Canceled'")
        .queryParam("exported", true)
        .queryParam("$skip", page)
        .request()
        .get(new GenericType<ApiListWrapper<Order>>() {});
  }

  /** Awknowledge that the order has beenc reated on our side. */
  @Override
  public void acknowledgeOrder(String brand, String marketplace, String id) {
    errorsToExceptions(
        provider
            .getAuthenticatedClient(brand)
            .path("/v1/orders({orderId})/Export")
            .resolveTemplate("orderId", id)
            .request()
            .post(Entity.json("")));
  }

  @Override
  public void shipOrderFullyOrPartialy(
      String brand, String marketplace, String orderId, FulfillmentEntity shipment) {
    errorsToExceptions(
        provider
            .getAuthenticatedClient(brand)
            .path("/v1/Orders({orderId})/Ship")
            .resolveTemplate("orderId", orderId)
            .request()
            .post(Entity.json(new ApiPost<>(new Shipment()))));
  }

  /** Partially cancel items on Skubana */
  @Override
  public void cancelItems(
      String brand, String marketplace, String orderId, String orderItemId, int qtyToCancel) {
    throw new UnsupportedOperationException();
  }

  // TODO: 24/11/17 Integrate with POST /service/v1.1/inventory/adjust Adjust product stock quantity
  @Override
  public void updateItemInventory(String brand, String productId, Optional<String> dcId, int qty) {
    UpdateQuantity update = UpdateQuantity.makeInventoryUpdate(dcId, qty);
    errorsToExceptions(
        provider
            .getAuthenticatedClient(brand)
            .path("/v1/Products({ProductID})/UpdateQuantity")
            .resolveTemplate("ProductID", productId)
            .request()
            .post((Entity.json(new ApiPost<>(update)))));
  }

  /** ===== HELPER FUNCTIONS. */

  /**
   * A helper method that will take a Jersey response and throw an exception if the response is not
   * successful (200).
   */
  public void errorsToExceptions(Response response) throws WebApplicationException {
    if (Response.Status.Family.familyOf(response.getStatus())
        != Response.Status.Family.SUCCESSFUL) {
      throw new WebApplicationException(response);
    }
  }

  /** A helper method that walks the paginated results that come back from Skubana. */
  public List<?> collectPages(BiFunction<String, Integer, ApiListWrapper<?>> fn, String brand) {
    List<Object> allOrders = Lists.newArrayList();

    ApiListWrapper<?> list = fn.apply(brand, null);
    allOrders.addAll(list.getValue());

    int pageCount = 1;
    while (list.hasMore()) {
      list = fn.apply(brand, pageCount);
      allOrders.addAll(list.getValue());
      pageCount++;
    }

    return allOrders;
  }

  @Override
  public void updateConfig(DeejayLiveConfig config) {}
}
