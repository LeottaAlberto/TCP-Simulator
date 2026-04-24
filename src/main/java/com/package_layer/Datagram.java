package com.package_layer;

import com.Interface.Packet;

public class Datagram implements Packet {
    private final String IPsrc;
    private final String IPDest;
    private final Segment payload;

    public Datagram(String IPsrc, String IPDest, Segment payload) {
        this.payload = payload;
        this.IPDest = IPDest;
        this.IPsrc = IPsrc;
    }

    @Override
    public Object getPayload() {
        return payload;
    }

    public String getIPsrc() {
        return IPsrc;
    }

    public String getIPDest() {
        return IPDest;
    }
}
