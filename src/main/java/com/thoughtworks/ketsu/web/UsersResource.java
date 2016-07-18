package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.core.UserRepository;
import com.thoughtworks.ketsu.infrastructure.records.UserRecord;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by syzhang on 7/18/16.
 */

@Path("users")
public class UsersResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> info,
                               @Context Routes routes,
                               @Context UserRepository userRepository){
        if(info.getOrDefault("name", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name is required.");
        if(userRepository.findUserByNname(String.valueOf(info.get("name"))).isPresent())
            return Response.status(Response.Status.BAD_REQUEST).build();

        User user = userRepository.createUser(info);
        return Response.created(routes.userUrl(user)).build();
    }
}
