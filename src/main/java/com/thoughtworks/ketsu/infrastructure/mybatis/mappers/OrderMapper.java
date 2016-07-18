package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.infrastructure.core.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public interface OrderMapper {
    int saveOrder(@Param("info")Map<String, Object> info);

    int saveOrderItems(@Param("list") List<Map<String, Object>> list);

    Order findOrderById(@Param("orderId") int orderId);

    List<Order> findOrders(@Param("userId") int userId);
}
