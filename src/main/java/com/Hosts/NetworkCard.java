package com.Hosts;

import javax.naming.Context;

import com.Enums.ApplicationProtocol;
import com.Interface.NetDevice;
import com.Utility.Check;
import com.Utility.Generate;
import com.layer.ApplicationLayer;

public class NetworkCard {
    private final ApplicationLayer appLayer;
    private final NetDevice ref;
    private String IP;
    private String MAC;

    public NetworkCard(NetDevice ref, String IP, String MAC) {
        this.ref = ref;
        this.IP = (Check.checkIP_IPV4(IP))
                ? IP
                : Generate.nextIP();
        this.MAC = MAC;
        this.appLayer = new ApplicationLayer(this);
    }

    public NetworkCard(NetworkCard ref) {
        this.ref = ref.getRef();
        this.IP = ref.getIP();
        this.MAC = ref.getMAC();
        this.appLayer = ref.getAppLayer();
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

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public void dataDecapsulation(String frame) {
        this.ref.onReceiveData(frame, MAC);
    }

    public void dataEncapsulation(String message, ApplicationProtocol protocol) {
        this.appLayer.send(message, protocol);
    }
}