package com.package_layer;

import com.Interface.PDU;

public class Segment implements PDU<Application> {
    private final int portSrc;
    private final int portDest;
    private final Application data;

    public Segment(Application data, int portSrc, int portDest) {
        this.data = data;
        this.portDest = portDest;
        this.portSrc = portSrc;
    }

    @Override
    public Application getPayload() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "portSrc=" + portSrc +
                ", portDest=" + portDest +
                ", data=" + data +
                '}'; 
    }

    public int getPortSrc() {
        return this.portSrc;
    }

    public int getPortDest() {
        return this.portDest;
    }
}
