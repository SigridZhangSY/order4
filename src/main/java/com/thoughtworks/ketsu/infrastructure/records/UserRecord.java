package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.User;

/**
 * Created by syzhang on 7/18/16.
 */
public class UserRecord implements User {
    private int id;
    private String name;

    public UserRecord(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
