package ru.sellersp.api;

import org.glassfish.jersey.server.ResourceConfig;
import ru.sellersp.api.provider.AuthenticationFilter;

/**
 * Created by Sanle on 08.05.2017.
 */
public class ServerConfiguration extends ResourceConfig {
    public ServerConfiguration() {
        packages("ru/sellersp/api/services");
       // register(AuthenticationFilter.class);
        register(AppBinder.class);
    }
}
