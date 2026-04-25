package com.layer;

import com.Interface.Layer;
import com.Interface.Packet;

public class TransportLayer implements Layer {

    @Override
    public void send(Packet packet) {
        // this.nextLayer.send(data, null);
    }

    @Override
    public void receive(Packet packet) {
        // this.host.dataDecapsulation(data);
    }
}