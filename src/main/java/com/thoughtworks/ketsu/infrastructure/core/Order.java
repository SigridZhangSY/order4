package com.thoughtworks.ketsu.infrastructure.core;

import com.thoughtworks.ketsu.infrastructure.records.OrderItemRecord;

import java.util.List;
import java.util.Map;

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
    String getTime();
    List<OrderItemRecord> getItems();

    Payment createPayment(Map<String, Object> info);
}
