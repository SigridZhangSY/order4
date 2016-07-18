package com.thoughtworks.ketsu.infrastructure.core;

import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/18/16.
 */
public interface UserRepository {
    User createUser(Map<String, Object> info);
    Optional<User> findUserByNname(String name);
}
