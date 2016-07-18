package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Order;
import com.thoughtworks.ketsu.infrastructure.core.Product;
import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public class UserRecord implements User, Record {
    private int id;
    private String name;

    @Inject
    OrderMapper orderMapper;
    @Inject
    ProductMapper productMapper;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Order createOrder(Map<String, Object> info) {
        float totalPrice = 0;
        info.put("user_id", id);
        orderMapper.saveOrder(info);
        int orderId = Integer.valueOf(String.valueOf(info.get("id")));
        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("order_items");
        for(int i = 0; i < items.size(); i++){
            Product product = productMapper.findById(Integer.valueOf(String.valueOf(items.get(i).get("product_id"))));
            items.get(i).put("amount", product.getPrice());
            items.get(i).put("order_id", orderId);
        }

        orderMapper.saveOrderItems(items);

        return orderMapper.findOrderById(orderId);

    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("id", id);
            put("uri", routes.userUrl(UserRecord.this));
            put("name", name);
        }};
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }
}
