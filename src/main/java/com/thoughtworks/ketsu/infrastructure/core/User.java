package com.thoughtworks.ketsu.infrastructure.core;

import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public interface User {
    int getId();
    String getName();

    Order createOrder(Map<String, Object> info);
}
