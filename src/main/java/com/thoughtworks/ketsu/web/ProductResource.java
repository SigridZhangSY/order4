package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Product;
import com.thoughtworks.ketsu.infrastructure.core.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.records.ProductRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */

@Path("products")
public class ProductResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Map<String, Object> info,
                                  @Context Routes routes,
                                  @Context ProductRepository productRepository){

        Product product = productRepository.createProduct(info);
        return Response.created(routes.productUrl(product)).build();
    }
}
