package com.package_layer;

import com.Interface.Packet;

public class Datagram implements Packet<Segment> {
    private final String IPsrc;
    private final String IPDest;
    private final Segment payload;
    private final boolean isARPconn;

    public Datagram(Segment payload, String IPsrc, String IPDest, boolean isARPconn) {
        this.payload = payload;
        this.IPDest = IPDest;
        this.IPsrc = IPsrc;
        this.isARPconn = isARPconn;
    }

    public Datagram(Segment payload, String IPsrc, String IPDest) {
        this.payload = payload;
        this.IPDest = IPDest;
        this.IPsrc = IPsrc;
        this.isARPconn = false;
    }

    @Override
    public Segment getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Datagram{" +
                "IPsrc='" + IPsrc + '\'' +
                ", IPDest='" + IPDest + '\'' +
                ", payload=" + payload +
                '}';
    }

    public String getIPsrc() {
        return this.IPsrc;
    }

    public String getIPDest() {
        return this.IPDest;
    }

    public boolean getIsARPconn() {
        return this.isARPconn;
    }
}
