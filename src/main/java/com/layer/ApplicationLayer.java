package com.layer;

import javax.naming.Context;

import com.Hosts.NetworkCard;
import com.Interface.Layer;

public class ApplicationLayer implements Layer {
    private final Layer<String, ?> nextLayer;
    private final NetworkCard host;

    public ApplicationLayer(NetworkCard host) {
        this.nextLayer = new TransportLayer();
        this.host = host;
    }

    @Override
    public Object getPayload() {
        return null;
    }

    @Override
    public void send(String data, Context ctx) {
        this.nextLayer.send(data, null);
    }

    @Override
    public void receive(String data) {
        this.host.dataDecapsulation(data);
    }

    @Override
    public void send(String data, Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }
}
