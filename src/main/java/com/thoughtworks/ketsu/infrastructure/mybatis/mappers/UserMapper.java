package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.infrastructure.core.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public interface UserMapper {
    int saveUser(@Param("info") Map<String, Object> info);
    User findUserById(@Param("userId") int userId);
}
