package com.layer;

import com.Interface.Layer;
import com.Interface.PDU;
import com.package_layer.Application;
import com.package_layer.Datagram;
import com.package_layer.Segment;

public class TransportLayer implements Layer {
    private final Layer nextLayer;
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
    public boolean send(PDU<?> packet, boolean isBroadcast) {
        if (packet instanceof Application a) {
            int port = a.getProt().getPort();
            return this.nextLayer.send(new Segment(a, port, port), isBroadcast);
        }

        return false;
    }

    @Override
    public boolean receive(PDU<?> packet) {
        if (packet instanceof Datagram s) {
            return this.prevLayer.receive(s.getPayload());
        }

        return false;
    }
}