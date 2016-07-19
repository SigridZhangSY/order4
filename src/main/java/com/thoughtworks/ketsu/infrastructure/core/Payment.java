package com.thoughtworks.ketsu.infrastructure.core;

/**
 * Created by syzhang on 7/19/16.
 */
public interface Payment {
    int getId();
    int getUserId();
    String getPayType();
    float getAmount();
    String getTime();
}
