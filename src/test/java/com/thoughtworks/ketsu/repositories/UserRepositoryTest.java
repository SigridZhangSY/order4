package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.infrastructure.core.*;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by syzhang on 7/18/16.
 */

@RunWith(DatabaseTestRunner.class)
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_find_user(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        assertThat(user.getName(), is("john"));
    }

    @Test
    public void should_find_user_by_name(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        User user_res = userRepository.findUserByName(user.getName()).orElseThrow(() -> new NotFoundException("user not exists"));
        assertThat(user_res.getId(), is(user.getId()));

    }

    @Test
    public void should_find_user_by_id(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        User user_res = userRepository.findUserById(user.getId()).orElseThrow(() -> new NotFoundException("user not exists"));
        assertThat(user_res.getId(), is(user.getId()));

    }

    @Test
    public void should_save_and_find_order(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));
        assertThat(order.getUserId(), is(user.getId()));
        assertThat(order.getTotalPrice(), is(product.getPrice()*2));
        assertThat(order.getItems().size(), is(1));
    }

    @Test
    public void should_list_orders_for_user(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));
        List<Order> orderList = user.listAllOrdersForUser();
        assertThat(orderList.size(), is(1));
        assertThat(orderList.get(0).getId(), is(order.getId()));
        assertThat(orderList.get(0).getTotalPrice(), is(product.getPrice()*2));
        assertThat(order.getItems().size(), is(1));
    }

    @Test
    public void should_find_order_by_id_for_user(){
        User user = userRepository.createUser(TestHelper.userMap("john"));
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));
        Order order = user.createOrder(TestHelper.orderMap("kayla", product.getId()));
        Order order_res = user.findOrderByIdForUser(order.getId());
        assertThat(order_res.getId(), is(order.getId()));
        assertThat(order_res.getTotalPrice(), is(product.getPrice()*2));
        assertThat(order_res.getItems().size(), is(1));
    }

}
