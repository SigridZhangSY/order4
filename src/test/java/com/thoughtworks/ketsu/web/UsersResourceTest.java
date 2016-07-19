package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.*;
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
import java.util.List;
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

    @Inject
    ProductRepository productRepository;

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

    @Test
    public void should_return_201_when_create_order_with_specified_parameter(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Response post = post("/users/" + user.getId() + "/orders", TestHelper.orderMap("john",product.getId()));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));

    }

    @Test
    public void should_return_400_when_name_is_null(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Map<String, Object> map = TestHelper.orderMap("john",product.getId());
        map.remove("name");
        Response post = post("/users/" + user.getId() + "/orders", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_400_when_create_order_with_no_exists_product(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Map<String, Object> map = TestHelper.orderMap("john",product.getId()+1);
        Response post = post("/users/" + user.getId() + "/orders", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_details_when_list_all_orders(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));
        Response get = get("/users/" + user.getId() + "/orders");
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final List<Map<String, Object>> res = get.readEntity(List.class);
        assertThat(res.size(), is(1));
        assertThat(res.get(0).get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId()));

    }

    @Test
    public void should_return_detail_when_find_a_order_by_id(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));

        Response get = get("/users/" + user.getId() + "/orders/" + order.getId());
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));


        final Map<String, Object> res = get.readEntity(Map.class);
        assertThat(res.get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId()));
    }

    @Test
    public void should_return_404_when_no_order_exists(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));
        Response get = get("/users/" + user.getId() + "/orders/" + (order.getId()+1));
        assertThat(get.getStatus(), is(HttpStatus.NOT_FOUND_404.getStatusCode()));
    }

    @Test
    public void should_return_201_when_create_payment_for_user(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));

        Response post = post("/users/" + user.getId() + "/orders/" + order.getId() + "/payment", TestHelper.paymentMap("CASH", Float.valueOf("100")));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        assertThat(post.getLocation().toString(), endsWith("/users/" + user.getId() + "/orders/" + order.getId() + "/payment"));
    }

    @Test
    public void should_return_400_when_create_payment_with_payment_exists(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));
        Payment payment = user.createPaymentForOrder(TestHelper.paymentMap("CASH", Float.valueOf("100")), order.getId());

        Response post = post("/users/" + user.getId() + "/orders/" + order.getId() + "/payment", TestHelper.paymentMap("CASH", Float.valueOf("100")));
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_400_when_create_payment_with_payType_is_null(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));
        Map<String, Object> map = TestHelper.paymentMap("CASH", Float.valueOf("100"));
        map.remove("pay_type");

        Response post = post("/users/" + user.getId() + "/orders/" + order.getId() + "/payment", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_200_when_find_payment_for_order(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));

        Response get = get("/users/" + user.getId() + "/orders/" + order.getId() + "/payment");
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
    }

}
