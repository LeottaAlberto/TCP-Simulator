package com.layer;

import com.Hosts.Host;
import com.Interface.Layer;
import com.Interface.Packet;

public class TransportLayer implements Layer {
    private final Layer nextLayer;
    private final Layer prevLayer;
    

    public TransportLayer(Layer ApplicationLayer, Host pass){
        this.nextLayer = new NetworkLayer(this, pass);
        this.prevLayer = ApplicationLayer;
    }

    @Override
    public void send(Packet packet) {
        this.nextLayer.send(packet);
    }

    @Override
    public void receive(Packet packet) {
        this.prevLayer.receive(packet);
    }
}