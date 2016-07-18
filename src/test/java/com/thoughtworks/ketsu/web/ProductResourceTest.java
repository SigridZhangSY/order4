package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Product;
import com.thoughtworks.ketsu.infrastructure.core.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/18/16.
 */

@RunWith(ApiTestRunner.class)
public class ProductResourceTest extends ApiSupport{

    @Inject
    ProductRepository productRepository;

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

    @Test
    public void should_return_detail_when_list_all_products(){
        Product product = productRepository.createProduct(TestHelper.productMap("apple", "red apple", Float.valueOf("1.2")));

        Response get = get("/products");
        final List<Map<String, Object>> res = get.readEntity(List.class);
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        assertThat(res.size(), is(1));
        assertThat(res.get(0).get("uri"), is("/products/" + String.valueOf(product.getId())));
    }
}
