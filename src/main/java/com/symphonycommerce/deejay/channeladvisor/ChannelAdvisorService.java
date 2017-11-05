package com.symphonycommerce.deejay.channeladvisor;

import com.symphonycommerce.deejay.ecommerce.EcommerceConnection;
import com.symphonycommerce.deejay.ecommerce.EcommerceController;
import com.symphonycommerce.deejay.ecommerce.EcommerceRegistrar;
import com.symphonycommerce.deejay.ecommerce.EcommerceService;
import com.symphonycommerce.deejay.ecommerce.ShippingMethodMapper;
import com.symphonycommerce.deejay.ecommerce.SimpleRegistrar;

import java.util.Set;

public class ChannelAdvisorService implements EcommerceService {

  private final EcommerceConnection connection;
  private final ChannelAdvisorLiveConfig config;

  public ChannelAdvisorService(ChannelAdvisorConnection connection,
      ChannelAdvisorLiveConfig config) {
    this.connection = connection;
    this.config = config;
  }

  @Override
  public Set<String> getBrands() {
    return config.getBrands();
  }

  @Override
  public String getServiceName() {
    return "CHANNEL_ADVISOR";
  }

  @Override
  public String getPurchasingChannelName() {
    return "Channel Advisor - Walmart";
  }

  @Override
  @Deprecated
  public String getExternalIdName() {
    return "CHANNEL_ADVISOR_WALMART";
  }

  @Override
  public void setupNewBrand(String name, String apiKey) {

  }

  @Override
  public EcommerceController getController() {
    return null;
  }


  @Override
  public EcommerceConnection getConnection() {
    return connection;
  }

  @Override
  public EcommerceRegistrar getRegistrar() {
    return new SimpleRegistrar(this);
  }

  @Override
  public ShippingMethodMapper getShippingMapper() {
    return new ShippingMethod();
  }
}
