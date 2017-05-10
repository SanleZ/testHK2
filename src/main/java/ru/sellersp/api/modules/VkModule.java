package ru.sellersp.api.modules;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Properties;

/**
 * Created by Sanle on 20.04.2017.
 */

@Service
public class VkModule {

    @Inject
    @Named("props")
    private PropertiesModule propertiesModule;

    private VkApiClient vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

    public VkModule(){
    }

    public UserActor getUserActor(String code) {
        try {
            Properties properties = propertiesModule.provideProperties();
            //http://api.vk.com/blank.html#code=a7c56982656428e801
            //https://vk.com/id40369786

            UserAuthResponse authResponse = vkApiClient.oauth()
                    .userAuthorizationCodeFlow(Integer.parseInt(properties.getProperty("vk.appId")),
                            properties.getProperty("vk.privateKey"),
                            properties.getProperty("http://vk.com/"),
                            code).execute();
            return new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
       //     return new UserActor(40369786, "c7cfda540706df2cfce54870975d402e6b784204eb4cf725cb89ae0541e5d6443686a6d6c1728277a189d");
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

}
