package com.layer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import com.Enums.ApplicationProtocol;
import com.Interface.Layer;
import com.Interface.PDU;
import com.package_layer.Application;
import com.package_layer.Datagram;
import com.package_layer.Frame;
import com.package_layer.Segment;

public class DatalinkLayer implements Layer {
    private final Layer nextLayer;
    private final String sourceMAC;
    private final HashMap<String, String> ARPCache;
    private Layer prevLayer;

    private final Map<String, CompletableFuture<String>> richiesteArpInSospeso = new ConcurrentHashMap<>();

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

    private CompletableFuture<String> ARP(Datagram data, String targetIP) {
        // 1. Creiamo una promessa "vuota"
        CompletableFuture<String> promessaMac = new CompletableFuture<>();

        // 2. Registriamo la promessa nella mappa: 
        richiesteArpInSospeso.put(targetIP, promessaMac);

        var f = new Frame(
            new Datagram(
                new Segment(
                    new Application("ARP_REQUEST", ApplicationProtocol.HTTPS),
                    data.getPayload().getPortSrc(),
                    data.getPayload().getPortDest()
                ), 
                data.getIPsrc(), 
                targetIP, 
                true
            ),
            this.sourceMAC, "FF:FF:FF:FF:FF:FF"
        );

        this.nextLayer.send(f, true);

        return promessaMac;
    }

    @Override
    public boolean send(PDU<?> pdu, boolean isBroadcast) {
        if (pdu instanceof Datagram d) {
            if (!this.ARPCache.containsKey(d.getIPDest()))
                ARP(d, d.getIPDest());

            return this.nextLayer
                    .send(new Frame(d, this.sourceMAC, this.ARPCache.get(d.getIPDest())), isBroadcast);
        }

        return false;
    }

    @Override
    public boolean receive(PDU<?> pdu) {
        if (pdu instanceof Frame f) {

            if (isArpReply(f)) {
        
                // Estrai i dati dal pacchetto di risposta
                String ipMittente = f.getPayload().getIPsrc();
                String macMittente = f.getMACSrc();

                // 2. Controlliamo se qualcuno stava aspettando questo IP
                CompletableFuture<String> promessa = richiesteArpInSospeso.remove(ipMittente);

                if (promessa != null) {
                    System.out.println("Risposta ARP ricevuta per IP: " + ipMittente);
                    this.ARPCache.put(ipMittente, macMittente);
                    promessa.complete(macMittente);
                }
            } 
            else 
                if (!f.getMACDest().equals("FF:FF:FF:FF:FF:FF") && !f.getMACDest().equals(this.sourceMAC))                    
                    return false;

                return this.prevLayer.receive(f.getPayload());
        }
        return false;
    }

    private boolean isArpReply(Frame f) {
        return f.getPayload().getPayload().getPayload().getMess().equals("ARP_REPLAy");
    }
}