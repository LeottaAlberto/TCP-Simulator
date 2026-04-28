package com.Interface;

public interface Layer {
    public boolean send(PDU<?> packet);

    public boolean receive(PDU<?> packet);
}