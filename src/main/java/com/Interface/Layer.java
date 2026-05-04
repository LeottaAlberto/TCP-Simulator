package com.Interface;

public interface Layer {
    public boolean send(PDU<?> pdu);

    public boolean receive(PDU<?> pdu);
}