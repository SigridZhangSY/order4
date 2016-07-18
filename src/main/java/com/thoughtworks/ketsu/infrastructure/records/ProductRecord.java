package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Product;

import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public class ProductRecord implements Product {
    private int id;
    private String name;
    private String description;
    private float price;

    public ProductRecord(){
        this.id = 1;
        this.name = "apple";
        this.description = "red apple";
        this.price = Float.valueOf("1.2");
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getPrice() {
        return price;
    }
}
