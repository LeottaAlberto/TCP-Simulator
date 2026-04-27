package com.layer;

import com.Interface.Layer;
import com.Interface.Packet;
import com.package_layer.Datagram;
import com.package_layer.Frame;

public class DatalinkLayer implements Layer {
    private Layer nextLayer;
    private Layer prevLayer;

    public DatalinkLayer(Layer physicalLayer) {
        this.nextLayer = physicalLayer;
    }

    public void setPrevLayer(Layer networkLayer) {
        if (networkLayer == null)
            return;

        this.prevLayer = networkLayer;
    }

    public Layer getNextLayer() {
        return this.nextLayer;
    }

    public Layer getPrevLayer() {
        return this.prevLayer;
    }

    @Override
    public void send(Packet<?> packet) {
        if (packet instanceof Datagram d) {
            Frame f = new Frame(d, "AA.BB.CC.DD", "BB.CC.DD.FF");
            this.nextLayer.send(f);
        }
    }

    @Override
    public void receive(Packet<?> packet) {
        if (packet instanceof Frame f) {
            this.prevLayer.receive(f.getPayload());
        }
    }

}