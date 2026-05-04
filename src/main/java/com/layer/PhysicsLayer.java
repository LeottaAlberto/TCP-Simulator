package com.layer;

import com.Interface.Layer;
import com.Interface.PDU;
import com.Interface.TransmissionChannel;
import com.package_layer.Frame;

public class PhysicsLayer implements Layer {
    private final TransmissionChannel channel;
    private Layer dataLinkLayer;

    public PhysicsLayer(TransmissionChannel channel) {
        this.channel = channel;
    }

    public void setNextLayer(Layer dataLinkLayer) {
        if (dataLinkLayer == null)
            return;

        this.dataLinkLayer = dataLinkLayer;
    }

    public Layer getDataLinkLayer() {
        return this.dataLinkLayer;
    }

    @Override
    public boolean send(PDU<?> pdu, boolean isBroadcast) {
        if (pdu instanceof Frame f) {
            System.out.println("Pacchetto in fase di invio: " + pdu.toString());
            return (f.getMACDest().equals("FF:FF:FF:FF:FF:FF")) ? this.channel.sendBroadcast(f) :  this.channel.sendOnWire(f);
        }
        return false;
    }

    @Override
    public boolean receive(PDU<?> pdu) {
        return this.dataLinkLayer.receive(pdu);
    }
}
