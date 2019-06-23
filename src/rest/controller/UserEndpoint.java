package rest.controller;

import rest.models.Coupon;
import rest.models.User;
import rest.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserEndpoint {

    private final UserService userService;

    public UserEndpoint() {
        this.userService = new UserService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addCoupon(User user) {
        return userService.addUser(user);
    }

    @GET
    @Path("{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("username") String username, @PathParam("password") String password) {

        return userService.getUser(username, password);

    }


}
