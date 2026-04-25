package com.layer;

import com.Hosts.NetworkCard;
import com.Interface.Layer;
import com.Interface.Packet;

public class ApplicationLayer implements Layer {
    // private final Layer<String, ?> nextLayer;
    private final NetworkCard host;

    public ApplicationLayer(NetworkCard host) {
        // this.nextLayer = new TransportLayer();
        this.host = host;
    }

    @Override
    public void send(Packet packet) {
        // this.nextLayer.send(data, null);
    }

    @Override
    public void receive(Packet packet) {
        // this.host.dataDecapsulation(data);
    }
}
