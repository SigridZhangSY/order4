package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.records.ProductRecord;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/18/16.
 */
public class ProductRepository implements com.thoughtworks.ketsu.infrastructure.core.ProductRepository {
    @Inject
    ProductMapper productMapper;

    @Override
    public Product createProduct(Map<String, Object> info) {

        productMapper.saveProduct(info);
        int productId = Integer.valueOf(String.valueOf(info.get("id")));

//        Product product = new ProductRecord(info);
//        productMapper.saveProduct(product);
//        int productId = product.getId();

        return productMapper.findById(productId);
    }

    @Override
    public List<Product> listAllProducts() {
        return productMapper.listAll();
    }

    @Override
    public Optional<Product> findProductById(int productId) {
        return Optional.ofNullable(productMapper.findById(productId));
    }
}
