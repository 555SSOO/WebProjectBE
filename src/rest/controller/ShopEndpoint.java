package rest.controller;

import rest.models.Coupon;
import rest.models.Shop;
import rest.service.ShopService;

import javax.ws.rs.*;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Shop addShop(Shop shop) {
        return service.addShop(shop);
    }

}
