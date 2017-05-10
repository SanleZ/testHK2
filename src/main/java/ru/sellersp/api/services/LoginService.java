package ru.sellersp.api.services;

import com.vk.api.sdk.client.actors.UserActor;
import org.jvnet.hk2.annotations.Service;
import ru.sellersp.api.beans.User;
import ru.sellersp.api.modules.Users;
import ru.sellersp.api.modules.VkModule;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Sanle on 09.05.2017.
 */
@Path("login")
public class LoginService {

    @Inject
    private Users users;

    @Inject
    private VkModule vkModule;

    @GET
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public String logIn(@QueryParam("code") String code) {
        UserActor userActor = vkModule.getUserActor(code);
        if (userActor != null) {
            User user = new User();
            user.setUserVkId(userActor.getId());
            user.setVkToken(userActor.getAccessToken());
            users.addUser(userActor.getAccessToken(), user);
            return "goooood";
        }
        return "no good";
    }
}
