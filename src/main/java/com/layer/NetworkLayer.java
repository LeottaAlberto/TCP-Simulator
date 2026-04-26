package com.layer;

import com.Interface.Layer;
import com.Interface.Packet;
import com.package_layer.Datagram;
import com.package_layer.Segment;

public class NetworkLayer implements Layer {
    private Layer nextLayer;
    private Layer prevLayer;

    public NetworkLayer(Layer TransportLayer) {
        this.nextLayer = TransportLayer;
    }

    public void setPrevLayer(Layer transportLayer) {
        if (transportLayer == null)
            return;

        this.prevLayer = transportLayer;
    }

    public Layer getNextLayer() {
        return this.nextLayer;
    }

    public Layer getPrevLayer() {
        return this.prevLayer;
    }

    @Override
    public void send(Packet<?> packet) {
        if (packet instanceof Segment s) {
            Datagram d = new Datagram(s, "192.168.1.1", "192.168.1.2");
            this.nextLayer.send(d);
        }
    }

    @Override
    public void receive(Packet<?> packet) {
        this.prevLayer.receive(packet);
    }

}
