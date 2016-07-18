package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/18/16.
 */

@RunWith(ApiTestRunner.class)
public class ProductResourceTest extends ApiSupport{

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_201_when_create_product_with_specified_parameter(){
        Response post = post("/products", TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

    @Test
    public void should_return_400_when_create_order_with_name_is_null(){
        Map<String, Object> info = TestHelper.productMap("apple", "red apple", Float.valueOf("1.2"));
        info.remove("name");
        Response post = post("/products", info);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }
}
