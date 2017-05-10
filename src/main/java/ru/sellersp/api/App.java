package ru.sellersp.api;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import ru.sellersp.api.modules.ServerModule;

/**
 * Created by Sanle on 08.05.2017.
 */
public class App {


    public static void main(String[] args) {
        ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServerModule serverModule = serviceLocator.getService(ServerModule.class);
        serverModule.start();
    }
}
