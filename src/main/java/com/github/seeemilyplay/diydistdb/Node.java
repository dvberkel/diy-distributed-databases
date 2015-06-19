package com.github.seeemilyplay.diydistdb;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class Node {

    static {
        ResteasyProviderFactory instance = ResteasyProviderFactory.getInstance();
        RegisterBuiltin.register(instance);
        instance.registerProvider(ResteasyJacksonProvider.class);
    }

    public static Thing getThing(String url, int id) throws Exception {
        String endpoint = url + "/things/" + id;
        ClientRequest req = new ClientRequest(endpoint);
        req.accept(MediaType.APPLICATION_JSON);
        ClientResponse<Thing> res = req.get(Thing.class);
        return res.getEntity();
    }

    public static Thing putThing(String url, Thing thing) throws Exception {
        String endpoint = url + "/things";
        ClientRequest req = new ClientRequest(endpoint);
        req.body(MediaType.APPLICATION_JSON, thing);
        ClientResponse<Thing> res = req.post(Thing.class);
        return res.getEntity();
    }
}
