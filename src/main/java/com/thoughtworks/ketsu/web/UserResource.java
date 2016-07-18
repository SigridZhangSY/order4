package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.core.UserRepository;
import com.thoughtworks.ketsu.infrastructure.records.OrderRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */
public class UserResource {
    private User user;

    public UserResource(User user) {
        this.user = user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserById(){
        return user;
    }

    @POST
    @Path("orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(Map<String, Object> info,
                                @Context Routes routes){
        return Response.created(routes.orderUrl(user.createOrder(info))).build();
    }
}
