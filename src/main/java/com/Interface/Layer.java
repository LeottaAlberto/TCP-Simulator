package com.Interface;

public interface Layer {
    public boolean send(PDU<?> packet, boolean isBroadcast);

    public boolean receive(PDU<?> packet);
}