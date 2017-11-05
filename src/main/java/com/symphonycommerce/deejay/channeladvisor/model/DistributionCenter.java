package com.symphonycommerce.deejay.channeladvisor.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.DistributionCenterEntity;
import com.symphonycommerce.deejay.ecommerce.entities.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistributionCenter implements DistributionCenterEntity {

  enum DcType {
    Warehouse,
    ExternallyManaged,
    DropShip,
    RetailStore
  }

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

  @JsonProperty("Name")
  public String getName() {
    return name;
  }

  @JsonProperty("Code")
  public String getCode() {
    return code;
  }

  @JsonProperty("Type")
  public DcType getType() {
    return type;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setType(DcType type) {
    this.type = type;
  }
}
