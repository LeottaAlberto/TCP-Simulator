package com.Hosts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.Enums.ApplicationProtocol;
import com.Interface.NetDevice;
import com.Interface.TransmissionChannel;
import com.Utility.Check;
import com.Utility.Generate;
import com.layer.ApplicationLayer;
import com.layer.DatalinkLayer;
import com.layer.NetworkLayer;
import com.layer.PhysicsLayer;
import com.layer.TransportLayer;
import com.package_layer.Application;

public class NetworkCard {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private final PhysicsLayer physicsLayer;
    private final DatalinkLayer dtLayer;
    private final NetworkLayer netLayer;
    private final TransportLayer transLayer;
    private final ApplicationLayer appLayer;
    
    private final NetDevice ref;
    private String IP;
    private String MAC;

    public NetworkCard(NetDevice ref, String IP, String MAC, TransmissionChannel channel) {
        this.ref = ref;
        this.IP = (Check.checkIP_IPV4(IP))
                ? IP
                : Generate.nextIP();
        this.MAC = MAC;

        this.physicsLayer = new PhysicsLayer(channel);
        this.dtLayer = new DatalinkLayer(this.physicsLayer, this.MAC);
        this.netLayer = new NetworkLayer(this.dtLayer, this.IP);
        this.transLayer = new TransportLayer(this.netLayer);
        this.appLayer = new ApplicationLayer(this.ref);

        this.physicsLayer.setNextLayer(dtLayer);
        this.dtLayer.setPrevLayer(netLayer);
        this.netLayer.setPrevLayer(transLayer);
        this.transLayer.setPreLayer(appLayer);
        this.appLayer.setNextLayer(transLayer);
    }

    public NetworkCard(NetworkCard ref) {
        this.ref = ref.getRef();
        this.IP = ref.getIP();
        this.MAC = ref.getMAC();
        this.appLayer = ref.getAppLayer();
        this.dtLayer = ref.getDtLayer();
        this.physicsLayer = ref.getPhysicsLayer();
        this.netLayer = ref.getNetLayer();
        this.transLayer = ref.getTransLayer();
    }

    public NetDevice getRef() {
        return this.ref;
    }

    public String getMAC() {
        return this.MAC;
    }

    public String getIP() {
        return this.IP;
    }

    public ApplicationLayer getAppLayer() {
        return this.appLayer;
    }

    public PhysicsLayer getPhysicsLayer() {
        return this.physicsLayer;
    }

    public DatalinkLayer getDtLayer() {
        return this.dtLayer;
    }

    public NetworkLayer getNetLayer() {
        return this.netLayer;
    }

    public TransportLayer getTransLayer() {
        return this.transLayer;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public void dataDecapsulation(String frame) {
        this.ref.onReceiveData(MAC);
    }

    public boolean dataEncapsulation(String message, ApplicationProtocol protocol, String IP) {
        
        this.executor.submit(() -> {
            this.netLayer.setDestIp(IP);
            return this.appLayer.send(new Application(message, protocol));
        });

        return false;
    }
}