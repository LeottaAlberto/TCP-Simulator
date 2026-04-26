package com.layer;

import com.Interface.Layer;
import com.Interface.Packet;

public class PhysicsLayer implements Layer {
    private Layer dataLinkLayer;

    public void setNextLayer(Layer dataLinkLayer) {
        if (dataLinkLayer == null)
            return;

        this.dataLinkLayer = dataLinkLayer;
    }

    public Layer getDataLinkLayer() {
        return this.dataLinkLayer;
    }

    @Override
    public void send(Packet<?> packet) {
        if (packet != null)
            System.out.println("Pacchetto Arrivato: " + packet.getPayload().toString());
        receive(packet);
    }

    @Override
    public void receive(Packet<?> packet) {
        this.dataLinkLayer.receive(packet);
    }
}
