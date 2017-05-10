package ru.sellersp.api.services;

import org.jvnet.hk2.annotations.Service;
import ru.sellersp.api.beans.User;
import ru.sellersp.api.modules.Users;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by Sanle on 08.05.2017.
 */
@Path("/users/{id:\\d+}")
@Service
public class UserService {

    @Inject
    private Users users;

    @RolesAllowed("ADMIN")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getUserProfile(@QueryParam("id") int id) {

        return users.getUserByID(id);
    }
}
