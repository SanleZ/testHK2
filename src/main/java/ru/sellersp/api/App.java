package ru.sellersp.api;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.servlet.ServletContainer;
import ru.sellersp.api.modules.ServerModule;

/**
 * Created by Sanle on 08.05.2017.
 */
public class App {


    public static void main(String[] args) {

        Server server = new Server(2222);
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new ServletContainer(new ServerConfiguration())), "/*");
        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{servletHandler});
        server.setHandler(handlerList);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }



      //  ServerModule serverModule = new ServerModule();
      //  serverModule.start();

//        ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
//        ServerModule serverModule = serviceLocator.getService(ServerModule.class);
//        serverModule.start();
    }
}
