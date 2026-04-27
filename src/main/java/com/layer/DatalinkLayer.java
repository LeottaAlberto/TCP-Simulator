package com.layer;

import java.util.HashMap;

import com.Interface.Layer;
import com.Interface.Packet;
import com.package_layer.Datagram;
import com.package_layer.Frame;

public class DatalinkLayer implements Layer {
    private final Layer nextLayer;
    private final String sourceMAC;
    private final HashMap<String, String> ARPCache;
    private Layer prevLayer;

    public DatalinkLayer(Layer physicalLayer, String MAC) {
        this.nextLayer = physicalLayer;
        this.sourceMAC = MAC;
        this.ARPCache = new HashMap<>();
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

    public void ARP(String IP) {

    }

    @Override
    public boolean send(Packet<?> packet) {
        if (packet instanceof Datagram d) {
            // if (!this.ARPCache.containsKey(d.getIPDest()))
            // return false;

            return this.nextLayer.send(new Frame(d, this.sourceMAC, this.ARPCache.get(d.getIPDest())));
        }

        return false;
    }

    @Override
    public boolean receive(Packet<?> packet) {
        if (packet instanceof Frame f) {
            return this.prevLayer.receive(f.getPayload());
        }
        return false;
    }

}