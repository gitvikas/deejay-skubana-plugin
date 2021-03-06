package com.symphonycommerce.deejay.skubana;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.symphonycommerce.deejay.store.DeejayLiveConfig;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "Deejay.Config")
public class SkubanaLiveConfig extends DeejayLiveConfig {

  //  private String appId;
  //  private String sharedSecret;
  private Map<String, String> brandToRefreshToken;

  @DynamoDBIgnore
  public Set<String> getBrands() {
    return brandToRefreshToken.keySet();
  }

  @DynamoDBAttribute(attributeName = "brandToRefreshToken")
  public Map<String, String> getBrandToRefreshToken() {
    return brandToRefreshToken;
  }

  public void setBrandToRefreshToken(Map<String, String> brandToRefreshToken) {
    this.brandToRefreshToken = brandToRefreshToken;
  }

  //  @DynamoDBAttribute(attributeName = "appId")
  //  public String getAppId() {
  //    return appId;
  //  }
  //
  //  public void setAppId(String appId) {
  //    this.appId = appId;
  //  }
  //
  //  @DynamoDBAttribute(attributeName = "sharedSecret")
  //  public String getSharedSecret() {
  //    return sharedSecret;
  //  }
  //
  //  public void setSharedSecret(String sharedSecret) {
  //    this.sharedSecret = sharedSecret;
  //  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    SkubanaLiveConfig that = (SkubanaLiveConfig) obj;
    return Objects.equals(brandToRefreshToken, that.brandToRefreshToken);
    //        && Objects.equals(sharedSecret, that.sharedSecret)
    //        && Objects.equals(appId, that.appId);
  }

  @Override
  public int hashCode() {
    //    return Objects.hash(appId, sharedSecret, brandToRefreshToken);
    return Objects.hash(brandToRefreshToken);
  }
}
