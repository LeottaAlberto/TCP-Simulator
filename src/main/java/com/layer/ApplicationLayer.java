package com.layer;

import com.Enums.ApplicationProtocol;
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
    public boolean send(PDU<?> pdu, boolean isBroadcast) {
        if (pdu instanceof Application a) {
            return this.nextLayer.send(a, isBroadcast);
        }
        return false;
    }

    @Override
    public boolean receive(PDU<?> pdu) {
        if (pdu instanceof Segment a) {
            if (a.getPayload().getMess().equals("ARP_REQUEST")) {
                System.out.println("Ricevuto messaggio di ARP: " + a.getPayload().getMess());
                return this.nextLayer.send(new Application("ARP_REPLAY", ApplicationProtocol.HTTPS), false);
            }

            return this.host.onReceiveData(a.getPayload().getMess());
        }
        return false;
    }

}
