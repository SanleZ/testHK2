package ru.sellersp.api.modules;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jvnet.hk2.annotations.Service;
import ru.sellersp.api.AppConfig;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Sanle on 09.05.2017.
 */

@Service
public class ServerModule {

    @Inject
    private PropertiesModule propertiesModule;

    private Server server;

    public Server getServer(){
        return server;
    }

    public ServerModule(){
    }

    public void start(){
        try {
            server = new Server(Integer.parseInt(propertiesModule.provideProperties().getProperty("server.port")));
          //  server = new Server(8080);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(new ServletHolder(new ServletContainer(new AppConfig())), "/rest/*");
        server.setHandler(servletContextHandler);

        try {
            server.start();
            server.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            server.destroy();
        }
    }

    public void stop(){
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            server.destroy();
        }
    }

    public void reload(){
        if (server.isRunning())
            stop();
        start();
    }
}
