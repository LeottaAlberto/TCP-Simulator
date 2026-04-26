package com.layer;

import com.Interface.Layer;
import com.Interface.Packet;
import com.package_layer.Application;
import com.package_layer.Segment;

public class TransportLayer implements Layer {
    private Layer nextLayer;
    private Layer prevLayer;

    public TransportLayer(Layer networkLayer) {
        this.nextLayer = networkLayer;
    }

    public void setPreLayer(Layer applicationLayer) {
        if (applicationLayer == null)
            return;

        this.prevLayer = applicationLayer;
    }

    @Override
    public void send(Packet<?> packet) {
        if (packet instanceof Application a) {
            Segment s = new Segment(a, 80, 80);
            this.nextLayer.send(s);
        }
    }

    @Override
    public void receive(Packet<?> packet) {
        this.prevLayer.receive(packet);
    }
}