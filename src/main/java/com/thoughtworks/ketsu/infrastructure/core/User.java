package com.thoughtworks.ketsu.infrastructure.core;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public interface User {
    int getId();

    String getName();

    Order createOrder(Map<String, Object> info);

    List<Order> listAllOrdersForUser();

    Order findOrderByIdForUser(int orderId);

    Payment createPaymentForOrder(Map<String, Object> info, int orderId);

    Payment createPaymentForOrder(int orderId);
}
