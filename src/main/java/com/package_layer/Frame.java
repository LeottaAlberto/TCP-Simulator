package com.package_layer;

import com.Interface.Packet;

public class Frame implements Packet<Datagram> {
    private final String MACSrc;
    private final String MACDest;
    private final Datagram payload;

    public Frame(Datagram payload, String MACSrc, String MACDest) {
        this.MACSrc = MACSrc;
        this.MACDest = MACDest;
        this.payload = payload;
    }

    public String getMACSrc() {
        return MACSrc;
    }

    public String getMACDest() {
        return MACDest;
    }

    @Override
    public Datagram getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "MACSrc='" + MACSrc + '\'' +
                ", MACDest='" + MACDest + '\'' +
                ", payload=" + payload +
                '}'; 
            }

}
