package com.thoughtworks.ketsu.infrastructure.core;

import com.thoughtworks.ketsu.infrastructure.records.OrderItemRecord;

import java.util.List;

/**
 * Created by syzhang on 7/18/16.
 */
public interface Order {
    int getId();
    int getUserId();
    String getName();
    String getPhone();
    String getAddress();
    float getTotalPrice();
    List<OrderItemRecord> getItems();
}
