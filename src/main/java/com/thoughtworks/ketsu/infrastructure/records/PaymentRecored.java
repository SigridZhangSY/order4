package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Payment;

/**
 * Created by syzhang on 7/19/16.
 */
public class PaymentRecored implements Payment {
    int id;
    int userId;
    String payType;
    float amount;
    String time;

    public PaymentRecored(int userId, int orderId){
        this.id = orderId;
        this.userId = userId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getPayType() {
        return payType;
    }

    @Override
    public float getAmount() {
        return amount;
    }

    @Override
    public String getTime() {
        return time;
    }
}
