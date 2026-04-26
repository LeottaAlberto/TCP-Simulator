package com.layer;

import com.Hosts.NetworkCard;
import com.Interface.Layer;
import com.Interface.Packet;

public class ApplicationLayer implements Layer {
    private final Layer nextLayer;
    private final NetworkCard host;

    public ApplicationLayer(NetworkCard host) {
        this.nextLayer = new TransportLayer(this, TODO); //edit TODO with Host index for physicsLayer.send()
        this.host = host;                                // for now is with Host direct object
    }

    @Override
    public void send(Packet packet) {
        this.nextLayer.send(packet);
    }

    @Override
    public void receive(Packet packet) {
        // this.host.dataDecapsulation(packet);
    }
}
