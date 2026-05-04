package com.Interface;

public interface Layer {
    public boolean send(PDU<?> pdu, boolean isBroadcast);

    public boolean receive(PDU<?> pdu);
}