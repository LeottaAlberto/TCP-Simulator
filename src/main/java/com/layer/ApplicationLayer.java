package com.layer;

import com.Interface.Layer;
import com.Interface.NetDevice;
import com.Interface.Packet;
import com.package_layer.Application;

public class ApplicationLayer implements Layer {
    private Layer nextLayer;
    private final NetDevice host;
    private final String hostname;

    public ApplicationLayer(NetDevice host, String hostname) {
        this.host = host;
        this.hostname = hostname;
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
    public void send(Packet<?> packet) {
        if (packet instanceof Application a) {
            this.nextLayer.send(a);
        }
    }

    @Override
    public void receive(Packet<?> packet) {
        if (packet instanceof Application a) {
            this.host.onReceiveData(this.hostname, a.getMess());
        }
    }

}
