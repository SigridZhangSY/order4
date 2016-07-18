package com.thoughtworks.ketsu.infrastructure.core;

/**
 * Created by syzhang on 7/18/16.
 */
public interface OrderItem {
    int getId();
    int getProductId();
    int getQuantity();
    float getAmount();
}
