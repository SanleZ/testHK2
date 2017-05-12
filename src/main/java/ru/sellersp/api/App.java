package ru.sellersp.api;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import ru.sellersp.api.modules.ServerModule;
import ru.sellersp.api.modules.VkModule;

import java.util.List;

/**
 * Created by Sanle on 08.05.2017.
 */
public class App {


    public static void main(String[] args) {
        ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServerModule serverModule = serviceLocator.getService(ServerModule.class);
        List<?> allServices = serviceLocator.getAllServices(new Filter() {
            @Override
            public boolean matches(Descriptor d) {
                return true;
            }
        });

    //   VkModule vkModule = serviceLocator.getService(VkModule.class);
    //    vkModule.getUserActor("123");
        serverModule.start();
    }
}
