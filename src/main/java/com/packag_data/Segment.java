package com.packag_data;

import com.Interface.Packet;

public class Segment implements Packet {
    private final int portSrc;
    private final int portDest;
    private final String data;

    public Segment(String data, int portSrc, int portDest) {
        this.data = data;
        this.portDest = portDest;
        this.portSrc = portSrc;
    }

    @Override
    public Object getPayload() {
        return this.data;
    }

    public int getPortSrc() {
        return this.portSrc;
    }

    public int getPortDest() {
        return this.portDest;
    }
}
