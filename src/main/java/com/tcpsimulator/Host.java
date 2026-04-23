package com.tcpsimulator;

import com.layer.*;

public class Host {
    private String name;
    private boolean status;
    private LayerInfo info;

    public Host(String name, String ip, String mac){
        this.name = name;
        this.info = new LayerInfo(this, ip, mac);
    }
}
