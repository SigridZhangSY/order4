package com.thoughtworks.ketsu.infrastructure.core;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public interface ProductRepository {
    Product createProduct(Map<String, Object> info);
    List<Product> listAllProducts();
}
