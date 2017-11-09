package com.symphonycommerce.deejay.skubana;

import com.symphonycommerce.deejay.ecommerce.EcommerceService;
import com.symphonycommerce.deejay.ecommerce.ServiceFactory;
import com.symphonycommerce.deejay.skubana.connector.ConnectionProvider;
import com.symphonycommerce.deejay.store.DynamoDbConnection;

/**
 * A simple bootstrap script that registers thew new channel advisor service associated to the
 * channel advisor service name (given a configuration from the DB). This is a bit ugly and I would
 * put it in the #hack category to be cleaned up. Open to suggestions. Perhaps ServiceLocator from
 * JDK 6 would be more cononical, but that is a little ugly as well.
 */
public class SkubanaServiceFactory implements ServiceFactory<SkubanaLiveConfig> {

  @Override
  public EcommerceService create(SkubanaLiveConfig liveConfig, DynamoDbConnection dbConnection) {
    SkubanaConnection skubanaConnection = new SkubanaConnection(new ConnectionProvider(liveConfig));
    return new SkubanaService(skubanaConnection, liveConfig);
  }

  @Override
  public Class configurationClass() {
    return SkubanaLiveConfig.class;
  }

  @Override
  public String serviceName() {
    return "SKUBANA";
  }
}
