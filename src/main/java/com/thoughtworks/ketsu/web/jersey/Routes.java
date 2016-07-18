package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.infrastructure.core.Order;
import com.thoughtworks.ketsu.infrastructure.core.Product;
import com.thoughtworks.ketsu.infrastructure.core.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public Routes() {
        baseUri = "/";
    }

    public URI productUrl(Product product){return URI.create("/products/" + String.valueOf(product.getId()));}

    public URI userUrl(User user){return URI.create("/users/" + user.getId());}

    public URI orderUrl(Order order){return URI.create("/users/" + order.getUserId() + "/orders/" + order.getId());}
}
