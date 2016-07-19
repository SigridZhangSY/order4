package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Order;
import com.thoughtworks.ketsu.infrastructure.core.Payment;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.PaymentMapper;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.ArrayList;
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

    @Inject
    PaymentMapper paymentMapper;

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
    public Payment createPayment(Map<String, Object> info) {
        info.put("order_id", id);
        paymentMapper.savePayment(info);
        return paymentMapper.findPaymentById(id);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap();
        map.put("uri", routes.orderUrl(OrderRecord.this));
        map.put("name", name);
        map.put("address", address);
        map.put("phone", phone);
        map.put("total_price", totalPrice);
        map.put("created_at", time);

        List<Map<String, Object>> itemsMap = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < items.size(); i++){
            Map<String, Object> itemMap = new HashMap();
            itemMap.put("product_id", items.get(i).getProductId());
            itemMap.put("quantity", items.get(i).getQuantity());
            itemMap.put("amount", items.get(i).getAmount());
            itemsMap.add(itemMap);
        }

        map.put("order_items", itemsMap);

        return map;
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
