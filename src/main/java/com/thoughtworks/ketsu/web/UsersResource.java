package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.core.UserRepository;
import com.thoughtworks.ketsu.infrastructure.records.OrderRecord;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/18/16.
 */

@Path("users")
public class UsersResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> info,
                               @Context Routes routes,
                               @Context UserRepository userRepository){
        if(info.getOrDefault("name", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name is required.");
        if(userRepository.findUserByName(String.valueOf(info.get("name"))).isPresent())
            return Response.status(Response.Status.BAD_REQUEST).build();

        User user = userRepository.createUser(info);
        return Response.created(routes.userUrl(user)).build();
    }

    @Path("{userId}")
    public UserResource getUserResource(@PathParam("userId") int userId,
                                        @Context UserRepository userRepository){
        User user = userRepository.findUserById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return new UserResource(user);
    }

//    @GET
//    @Path("{userId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public User findUserById(@PathParam("userId") int userId,
//                             @Context UserRepository userRepository){
//        return userRepository.findUserById(userId).orElseThrow(() -> new NotFoundException("user not exists"));
//    }
//
//    @POST
//    @Path("{userId}/orders")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createOrder(@PathParam("userId") int userId,
//                                @Context Routes routes){
//        return Response.created(routes.orderUrl(new OrderRecord(userId, 1))).build();
//    }
}
