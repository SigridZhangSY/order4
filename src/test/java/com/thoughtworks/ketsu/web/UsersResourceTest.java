package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.core.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/18/16.
 */

@RunWith(ApiTestRunner.class)
public class UsersResourceTest extends ApiSupport {

    @Inject
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_uri_when_create_user_with_specified_parameter(){
        Response post = post("/users", TestHelper.userMap("john"));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

    @Test
    public void should_return_400_when_create_user_with_user_exists(){
        userRepository.createUser(TestHelper.userMap("john"));
        Response post = post("/users", TestHelper.userMap("john"));
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_400_when_create_user_with_name_is_null(){
        Response post = post("/users", new HashMap<String, Object>());
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_detail_when_find_user_by_id(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Response get = get("/users/" + user.getId());
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final Map<String, Object> user_res = get.readEntity(Map.class);
        assertThat(user_res.get("id"), is(user.getId()));
        assertThat(user_res.get("uri"), is("/users/" + user.getId()));
    }

    @Test
    public void should_return_404_when_no_user_exists(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Response get = get("/users/" + (user.getId()+1));
        assertThat(get.getStatus(), is(HttpStatus.NOT_FOUND_404.getStatusCode()));
    }
}
