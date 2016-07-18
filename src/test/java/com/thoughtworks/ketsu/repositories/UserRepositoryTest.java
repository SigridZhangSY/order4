package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.core.UserRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by syzhang on 7/18/16.
 */

@RunWith(DatabaseTestRunner.class)
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_and_find_user(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        assertThat(user.getName(), is("john"));

    }
}