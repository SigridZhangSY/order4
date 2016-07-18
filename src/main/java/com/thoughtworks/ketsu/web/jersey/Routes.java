package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.infrastructure.core.Product;

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

}
