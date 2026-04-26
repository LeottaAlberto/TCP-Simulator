package com.package_layer;

import com.Interface.Packet;

public class Datagram implements Packet<Segment> {
    private final String IPsrc;
    private final String IPDest;
    private final Segment payload;

    public Datagram(Segment payload, String IPsrc, String IPDest) {
        this.payload = payload;
        this.IPDest = IPDest;
        this.IPsrc = IPsrc;
    }

    @Override
    public Segment getPayload() {
        return payload;
    }

    public String getIPsrc() {
        return IPsrc;
    }

    public String getIPDest() {
        return IPDest;
    }
}
