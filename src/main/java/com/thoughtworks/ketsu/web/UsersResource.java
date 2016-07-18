package com.thoughtworks.ketsu.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by syzhang on 7/18/16.
 */

@Path("users")
public class UsersResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(){
        return Response.status(201).build();
    }
}
