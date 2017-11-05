package com.symphonycommerce.deejay.channeladvisor;

import com.symphonycommerce.deejay.channeladvisor.connector.ConnectionProvider;
import com.symphonycommerce.deejay.ecommerce.EcommerceService;
import com.symphonycommerce.deejay.ecommerce.ServiceFactory;
import com.symphonycommerce.deejay.store.DynamoDbConnection;

/**
 * A simple bootstrap script that registers thew new channel advisor service associated
 * to the channel advisor service name (given a configuration from the DB). This is a bit
 * ugly and I would put it in the #hack category to be cleaned up. Open to suggestions. Perhaps
 * ServiceLocator from JDK 6 would be more cononical, but that is a little ugly as well.
 */
public class ChannelAdvisorServiceFactory implements ServiceFactory<ChannelAdvisorLiveConfig> {

  @Override
  public EcommerceService create(ChannelAdvisorLiveConfig liveConfig,
      DynamoDbConnection dbConnection) {
    ChannelAdvisorConnection channelAdvisorConnection = new ChannelAdvisorConnection(
        new ConnectionProvider(liveConfig));
    return new ChannelAdvisorService(channelAdvisorConnection, liveConfig);
  }

  @Override
  public Class configurationClass() {
    return ChannelAdvisorLiveConfig.class;
  }

  @Override
  public String serviceName() {
    return "CHANNEL_ADVISOR";
  }
}
