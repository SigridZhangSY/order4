package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Order;
import com.thoughtworks.ketsu.infrastructure.core.Payment;
import com.thoughtworks.ketsu.infrastructure.core.User;
import com.thoughtworks.ketsu.infrastructure.core.UserRepository;
import com.thoughtworks.ketsu.infrastructure.records.OrderRecord;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Map<String, Object> info,
                                @Context Routes routes){
        if(info.getOrDefault("name", "").toString().trim().isEmpty() ||
                info.getOrDefault("address", "").toString().trim().isEmpty() ||
                info.getOrDefault("phone", "").toString().trim().isEmpty() ||
                info.getOrDefault("order_items", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name, address, phone and order_items are required");
        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("order_items");
        if(items.size() == 0)
            throw new InvalidParameterException("order_items can't be empty");
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i).getOrDefault("product_id", "").toString().trim().isEmpty() ||
                    items.get(i).getOrDefault("quantity", "").toString().trim().isEmpty())
                throw new InvalidParameterException("product_id and quantity are required");
        }
            return Response.created(routes.orderUrl(user.createOrder(info))).build();
    }

    @GET
    @Path("orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> listAllOrders(){
        return user.listAllOrdersForUser();
    }

    @GET
    @Path("orders/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order findOrderById(@PathParam("orderId") int orderId){
        return user.findOrderByIdForUser(orderId);
    }

    @POST
    @Path("orders/{orderId}/payment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPaymentForOrder(Map<String, Object> info,
                                          @PathParam("orderId") int orderId,
                                          @Context Routes routes){
        if(info.getOrDefault("pay_type", "").toString().trim().isEmpty() ||
                info.getOrDefault("amount", "").toString().trim().isEmpty())
            throw new InvalidParameterException("pay_type and amount are required");
        return Response.created(routes.paymentUri(user.createPaymentForOrder(info, orderId))).build();
    }

    @GET
    @Path("orders/{orderId}/payment")
    @Produces(MediaType.APPLICATION_JSON)
    public Payment findPaymentForOrder(@PathParam("orderId") int orderId){
        return user.findPaymentForOrder(orderId);
    }

}
