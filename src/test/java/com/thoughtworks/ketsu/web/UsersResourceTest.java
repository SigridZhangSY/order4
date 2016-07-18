package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/18/16.
 */

@RunWith(ApiTestRunner.class)
public class UsersResourceTest extends ApiSupport {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_uri_when_create_user_with_specified_parameter(){
        Response post = post("/users", TestHelper.userMap("john"));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }
}