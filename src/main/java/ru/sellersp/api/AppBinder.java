package ru.sellersp.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.sellersp.api.modules.ServerModule;
import ru.sellersp.api.modules.Users;
import ru.sellersp.api.modules.VkModule;

import javax.inject.Singleton;

/**
 * Created by Sanle on 09.05.2017.
 */
public class AppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(Users.class).to(Users.class);
        bind(VkModule.class).to(VkModule.class);
    }
}
