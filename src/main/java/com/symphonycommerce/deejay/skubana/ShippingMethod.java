package com.symphonycommerce.deejay.skubana;

import com.google.common.collect.ImmutableMap;

import com.symphonycommerce.deejay.ecommerce.ShippingMethodMapper;

public class ShippingMethod implements ShippingMethodMapper {

  /**
   * Provided a Symhony shipping method string like "FEDEX.OVERNIGHT" this method will return the
   * Channel Advisor carrier class.
   */
  public String getShippingClass(String symphonyCode) {
    String encoded = map.get(symphonyCode);
    if (encoded == null) {
      return null;
    }

    return encoded.split("\\.")[1];
  }

  /**
   * Provided a Symhony shipping method string like "FEDEX.OVERNIGHT" this method will return the
   * Channel Advisor carrier code.
   */
  public String getCarrierCode(String symphonyCode) {
    String encoded = map.get(symphonyCode);
    if (encoded == null) {
      return null;
    }

    return encoded.split("\\.")[0];
  }

  public static final ImmutableMap<String, String> map =
      new ImmutableMap.Builder<String, String>()
          .put("DHL.EXPRESS", "DHL.DHL")
          .put("DHL.GROUND", "DHL.DHL")
          .put("DHL.INTERNATIONAL.GROUND", "DHL.DHL")
          .put("FEDEX.2DAY.SHIPPING", "FEDEX.2DAY")
          .put("FEDEX.EXPRESS.SAVER.ECONOMY", "FEDEX.EXPSAVER")
          .put("FEDEX.GROUND", "FEDEX.GROUND")
          .put("FEDEX.HOME.DELIVERY", "FEDEX.GROUND")
          .put("FEDEX.INTERNATIONAL.ECONOMY", "FEDEX.INTLECONOMY")
          .put("FEDEX.INTERNATIONAL.GROUND", "FEDEX.INTLFIRST")
          .put("FEDEX.INTERNATIONAL.PRIORITY", "FEDEX.INTLPRIORITY")
          .put("FEDEX.OVERNIGHT.STANDARD", "FEDEX.OVERNIGHT")
          .put("FEDEX.PRIORITY.OVERNIGHT", "FEDEX.PRIORITY")
          .put("FEDEX.SMARTPOST.PRIORITY", "FEDEX.PRIORITY")
          .put("FEDEX.SMARTPOST.STANDARD", "FEDEX.GROUND")
          .put("UPS.2ND.DAY.AIR", "UPS.2DAY")
          .put("UPS.2ND.DAY.AIR.AM", "UPS.2DAA")
          .put("UPS.3.DAY.SELECT", "UPS.3DS")
          .put("UPS.GROUND", "UPS.GROUND")
          .put("UPS.NEXT.DAY.AIR", "UPS.NEXTDAY")
          .put("UPS.NEXT.DAY.AIR.AM", "UPS.NDAEA")
          .put("UPS.NEXT.DAY.AIR.SAVER", "UPS.NDAS")
          .put("UPS.STANDARD.CANADA", "UPS.GROUND")
          .put("UPS.WORLDWIDE.EXPEDITED", "UPS.WWEX")
          .put("UPS.WORLDWIDE.EXPRESS", "UPS.WWE")
          .put("UPS.WORLDWIDE.EXPRESS.PLUS.", "UPS.WWEP")
          .put("UPS.WORLDWIDE.EXPRESS.SAVER", "UPS.ES")
          .put("USPS.EXPRESS.MAIL", "USPS.EXPRESS")
          .put("USPS.EXPRESS.MAIL.INTERNATIONAL", "USPS.GEM")
          .put("USPS.FIRST.CLASS.MAIL", "USPS.FIRSTCLASS")
          .put("USPS.FIRSTCLASS.MAIL.INTERNATIONAL", "USPS.IFIRSTCLASS")
          .put("USPS.PRIORITY", "USPS.PRIORITY")
          .put("USPS.PRIORITY.MAIL.INTERNATIONAL", "USPS.IPRIORITY")
          .build();
}
