package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.records.UserRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by syzhang on 7/18/16.
 */

@Path("users")
public class UsersResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Context Routes routes){
        return Response.created(routes.userUrl(new UserRecord(1, "John"))).build();
    }
}
