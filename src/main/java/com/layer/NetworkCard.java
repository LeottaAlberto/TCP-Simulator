package com.layer;

import com.Interface.NetDevice;

public class NetworkCard {
    private final NetDevice ref;
    private String IP;
    private String MAC;

    public NetworkCard(NetDevice ref, String ip, String mac) {
        this.ref = ref;
        this.IP = ip;
        this.MAC = mac;
    }

    public String getMAC() {
        return this.MAC;
    }

    public String getIP() {
        return this.IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

}
