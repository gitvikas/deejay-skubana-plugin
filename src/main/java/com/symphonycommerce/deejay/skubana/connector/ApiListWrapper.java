package com.symphonycommerce.deejay.skubana.connector;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.symphonycommerce.deejay.ecommerce.entities.Entity;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties
public class ApiListWrapper<T extends Entity>
    implements com.symphonycommerce.deejay.ecommerce.ApiListWrapper<T> {

  List<T> value;

  @JsonProperty(value = "@odata.context", required = true)
  String schema;

  @JsonProperty(value = "@odata.nextLink", required = false)
  String nextLink;

  public List<T> getValue() {
    return value;
  }

  /** Looks to see if there are more items that need to be fetched. */
  public boolean hasMore() {
    return nextLink != null;
  }

  /** Returns the offset that should be applied to get the next set of items in the list. */
  public Optional<Integer> getSkipAmount() {
    Optional<NameValuePair> pair =
        URLEncodedUtils.parse(nextLink, Charset.defaultCharset())
            .stream()
            .filter(x -> x.getName().equals("$skip"))
            .findFirst();

    return pair.isPresent()
        ? Optional.of(Integer.parseInt(pair.get().getValue()))
        : Optional.empty();
  }
}
