package com.symphonycommerce.deejay.skubana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.DistributionCenterEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistributionCenter implements DistributionCenterEntity {

  private Integer id;
  private String name;
  private String code;
  private DcType type;

  @Override
  public String getIdString() {
    return id.toString();
  }

  @JsonProperty("ID")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("Code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @JsonProperty("Type")
  public DcType getType() {
    return type;
  }

  public void setType(DcType type) {
    this.type = type;
  }

  enum DcType {
    Warehouse,
    ExternallyManaged,
    DropShip,
    RetailStore
  }
}
