package com.thoughtworks.ketsu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by syzhang on 7/18/16.
 */

@Path("products")
public class ProductResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(){
        return Response.status(201).build();
    }
}
