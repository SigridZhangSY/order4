package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Order;
import com.thoughtworks.ketsu.infrastructure.core.Product;
import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            Optional<Product> product = Optional.ofNullable(productMapper.findById(Integer.valueOf(String.valueOf(items.get(i).get("product_id")))));
            if(product.isPresent() == false)
                throw new InvalidParameterException("product not exists");
            items.get(i).put("amount", product.orElseThrow(() -> new NotFoundException("product not exists")).getPrice());
            items.get(i).put("order_id", orderId);
        }

        orderMapper.saveOrderItems(items);

        return orderMapper.findOrderById(orderId);

    }

    @Override
    public List<Order> listAllOrdersForUser() {
        return orderMapper.findOrders(id);
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
    public Order findOrderByIdForUser(int orderId) {
        return orderMapper.findOrderById(orderId);
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }
}
