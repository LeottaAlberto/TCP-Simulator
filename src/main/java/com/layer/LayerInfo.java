package com.layer;

import com.tcpsimulator.Host;

public class LayerInfo {
    private Host host_ptr; // sostituire con Interface
    private String ip; 
    private String mac;

    public LayerInfo(Host _host_ptr, String _ip, String _mac){
        this.host_ptr = _host_ptr;
        this.ip = _ip;
        this.mac = _mac;
    }
}
