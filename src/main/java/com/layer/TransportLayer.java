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
            int port = a.getProt().getPort();
            this.nextLayer.send(new Segment(a, port, port));
        }
    }

    @Override
    public void receive(Packet<?> packet) {
        if (packet instanceof Segment s) {
            this.prevLayer.receive(s.getPayload());
        }
    }
}