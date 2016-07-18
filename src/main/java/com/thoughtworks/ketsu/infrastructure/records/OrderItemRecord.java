package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.OrderItem;

/**
 * Created by syzhang on 7/18/16.
 */
public class OrderItemRecord implements OrderItem{
    private int id;
    private int productId;
    private int quantity;
    private float amount;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public float getAmount() {
        return amount;
    }
}
