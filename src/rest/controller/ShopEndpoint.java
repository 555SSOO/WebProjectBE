package rest.controller;

import rest.models.Shop;
import rest.service.ShopService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shops")
public class ShopEndpoint {
    private final ShopService service;

    public ShopEndpoint() {
        this.service = new ShopService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> getShops() {
        return service.getShops();
    }

}
