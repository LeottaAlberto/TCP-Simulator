package com.layer;

import com.Hosts.Host;
import com.Interface.Layer;
import com.Interface.Packet;

public class DatalinkLayer implements Layer{
    private final Layer nextLayer;
    private final Layer prevLayer;

    public DatalinkLayer(Layer Networklayer, Host pass){
        this.nextLayer = new PhysicsLayer(this, pass);
        this.prevLayer = Networklayer;
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