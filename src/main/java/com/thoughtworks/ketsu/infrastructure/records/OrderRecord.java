package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Order;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public class OrderRecord implements Order, Record {
    private int id;
    private int userId;
    private String name;
    private String phone;
    private String address;
    private float totalPrice;
    private String time;
    private List<OrderItemRecord> items;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public List<OrderItemRecord> getItems() {
        return items;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return null;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uri",routes.orderUrl(OrderRecord.this));
            put("name",name);
            put("address",address);
            put("phone",phone);
            put("created_at",time);
        }};

    }
}
