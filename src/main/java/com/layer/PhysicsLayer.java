package com.layer;

import com.Hosts.Host;
import com.Interface.Layer;
import com.Interface.Packet;


public class PhysicsLayer implements Layer {
    private final Host endHost;
    private final Layer prevLayer;
    
    public PhysicsLayer(Layer DatalinkLayer, Host endhost){
        this.endHost = endhost;
        this.prevLayer = DatalinkLayer;
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
