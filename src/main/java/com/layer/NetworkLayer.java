package com.layer;

import com.Hosts.Host;
import com.Interface.Layer;
import com.Interface.Packet;

public class NetworkLayer implements Layer{
    private final Layer nextLayer;
    private final Layer prevLayer;

    public NetworkLayer(Layer TransportLayer, Host pass) {
        this.nextLayer = new DatalinkLayer(this, pass);
        this.prevLayer = TransportLayer;
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
