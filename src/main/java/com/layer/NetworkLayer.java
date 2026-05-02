package com.layer;

import com.Interface.Layer;
import com.Interface.PDU;
import com.package_layer.Datagram;
import com.package_layer.Segment;

public class NetworkLayer implements Layer {
    private final Layer nextLayer;
    private final String sourceIP;
    private Layer prevLayer;
    private String destIP;

    public NetworkLayer(Layer TransportLayer, String IP) {
        this.nextLayer = TransportLayer;
        this.sourceIP = IP;
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
    public boolean send(PDU<?> packet, boolean isBroadcast) {
        if (packet instanceof Segment s) {
            if (this.destIP == null && !isBroadcast)
                return false;
            return this.nextLayer.send(new Datagram(s, this.sourceIP, this.destIP), isBroadcast);
        }
        return false;
    }

    @Override
    public boolean receive(PDU<?> packet) {
        return this.prevLayer.receive(packet);
    }

    public void setDestIp(String IP) {
        this.destIP = IP;
    }
}
