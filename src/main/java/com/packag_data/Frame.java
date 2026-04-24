package com.packag_data;

import com.Interface.Packet;

public class Frame implements Packet {
    private final String MACSrc;
    private final String MACDest;
    private final Datagram payload;

    public Frame(String MACSrc, String MACDest, Datagram payload) {
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

}
