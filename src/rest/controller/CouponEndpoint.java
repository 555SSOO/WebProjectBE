package rest.controller;

import rest.models.Coupon;
import rest.repository.Repository;
import rest.service.CouponService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Controller ili Endpoint ce se baviti povezivanjem
 * HTTP Method/URL do biznis logike.
 *
 * CouponsEndpoint se bazira oko Kupon domena.
 * Svi endpoint-i su posveceni samo ovom resursu.
 */
@Path("/coupons")
public class CouponEndpoint {
    private final CouponService service;

    public CouponEndpoint() {
        this.service = new CouponService();
    }

    /**
     * Putanja je GET /rest/coupons
     * @return Vraca sve kupone na sistemu.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coupon> getCoupons() {

        return service.getCoupons();
    }

    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coupon> getActiveCoupons() {
        return service.getActiveCoupons();
    }

    /**
     * Putanja je POST /rest/coupons
     *
     * Obratite paznju da ne samo sto saljemo JSON @Produces(MediaType.APPLICATION_JSON)
     * nego ga i ocekujemo! Navesti Content-Type heder na FE. Potpis poslatog JSON-a
     * mora da se poklapa sa potpisom modela u argumentu funkcije.
     *
     * @param c Model kupona koji treba snimiti posto je POST metoda.
     * @return Vraca novododati kupon sa sve ID-jem u sebi.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Coupon addCoupon(Coupon c) {
        return service.addCoupon(c);
    }

    /**
     * Putanja je npr. DELETE /rest/coupons/1
     *
     * Primetite da upotrebom @Path("{id}") u kombinaciji sa @PathParam("id") Integer id
     * smo objasnili aplikaciji da prosledjeni "id" u putanji treba da bude argument funkcije.
     *
     * @param id
     * @return Vraca status 200 OK
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCoupon(@PathParam("id") Integer id) {
        service.deleteCoupon(id);

        return Response.ok().build();
    }

    @GET
    @Path("{shopId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coupon> getCoupons(@PathParam("shopId") Integer id) {
        return service.getCouponsForShop(id);
    }

}
