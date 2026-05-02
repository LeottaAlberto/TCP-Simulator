package com.layer;

import com.Interface.Layer;
import com.Interface.NetDevice;
import com.Interface.PDU;
import com.package_layer.Application;
import com.package_layer.Segment;

public class ApplicationLayer implements Layer {
    private Layer nextLayer;
    private final NetDevice host;

    public ApplicationLayer(NetDevice host) {
        this.host = host;
    }

    public void setNextLayer(Layer transportLayer) {
        this.nextLayer = transportLayer;
    }

    public Layer getNextLayer() {
        return this.nextLayer;
    }

    public NetDevice getHost() {
        return this.host;
    }

    @Override
    public boolean send(PDU<?> packet, boolean isBroadcast) {
        if (packet instanceof Application a) {
            return this.nextLayer.send(a, isBroadcast);
        }
        return false;
    }

    @Override
    public boolean receive(PDU<?> packet) {
        if (packet instanceof Segment a) {
            return this.host.onReceiveData(a.getPayload().getMess());
        }
        return false;
    }

}
