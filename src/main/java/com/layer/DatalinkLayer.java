package com.layer;

import com.Interface.Layer;
import com.Interface.PDU;
import com.package_layer.Datagram;
import com.package_layer.Frame;

public class DatalinkLayer implements Layer {
    private final Layer nextLayer;
    private final String sourceMAC;
    // private final HashMap<String, String> ARPCache;
    private Layer prevLayer;

    public DatalinkLayer(Layer physicalLayer, String MAC) {
        this.nextLayer = physicalLayer;
        this.sourceMAC = MAC;
        // this.ARPCache = new HashMap<>();
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

    // public void ARP(Datagram data, String IP) {
    // var f = new Frame(
    // new Datagram(new Segment(new Application("Are You?",
    // ApplicationProtocol.HTTPS),
    // data.getPayload().getPortSrc(),
    // data.getPayload().getPortDest()), data.getIPsrc(), data.getIPDest(), true),
    // this.sourceMAC, "FF:FF:FF:FF:FF:FF");
    // }

    @Override
    public boolean send(PDU<?> packet) {
        if (packet instanceof Datagram d) {
            // if (!this.ARPCache.containsKey(d.getIPDest()))
            // ARP(d.getIPDest());

            return this.nextLayer
                    .send(new Frame(d, this.sourceMAC, "82:F9:12:0B:AC:34"/* this.ARPCache.get(d.getIPDest()) */));
        }

        return false;
    }

    @Override
    public boolean receive(PDU<?> packet) {
        if (packet instanceof Frame f) {
            return this.prevLayer.receive(f.getPayload());
        }
        return false;
    }

}