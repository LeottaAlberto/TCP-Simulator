package com.layer;

import com.tcpsimulator.Host;

public class NetworkCard {
    private Host host_ptr; // sostituire con Interface
    private String IP;
    private String MAC;

    public NetworkCard(Host host_ptr, String ip, String mac) {
        this.host_ptr = host_ptr;
        this.IP = ip;
        this.MAC = mac;
    }

    public Host getHost_ptr() {
        return this.host_ptr;
    }

    public String getMAC() {
        return this.MAC;
    }

    public String getIP() {
        return this.IP;
    }

    public void setHost_ptr(Host host_ptr) {
        this.host_ptr = host_ptr;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

}
