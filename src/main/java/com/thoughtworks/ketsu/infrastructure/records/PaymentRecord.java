package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Payment;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syzhang on 7/19/16.
 */
public class PaymentRecord implements Payment, Record {
    int id;
    int userId;
    String payType;
    float amount;
    String time;

    public PaymentRecord(int userId, int orderId){
        this.id = orderId;
        this.userId = userId;
    }

    public PaymentRecord(){

    }

    @Inject
    OrderMapper orderMapper;

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

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_uri", routes.orderUrl(orderMapper.findOrderById(id)));
        map.put("uri", routes.paymentUri(PaymentRecord.this));
        map.put("payment_type", payType);
        map.put("amount", amount);

        return map;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }
}
