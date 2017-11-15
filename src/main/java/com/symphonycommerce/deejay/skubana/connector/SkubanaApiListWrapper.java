package com.symphonycommerce.deejay.skubana.connector;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.symphonycommerce.deejay.ecommerce.ApiListWrapper;
import com.symphonycommerce.deejay.ecommerce.entities.Entity;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties
public class SkubanaApiListWrapper<T extends Entity> implements ApiListWrapper<T> {
  public static final int PAGE_LIMIT = 1;
  List<T> value;

  public void setValue(List<T> value) {
    this.value = value;
  }

  public List<T> getValue() {
    return value;
  }

  /** Looks to see if there are more items that need to be fetched. */
  public boolean hasMore() {
    return value != null && value.size() == PAGE_LIMIT;
  }

  /** Returns the offset that should be applied to get the next set of items in the list. */
  public Optional<Integer> getSkipAmount() {
    throw new IllegalStateException("This is not supported for Skubana!");
  }
}
