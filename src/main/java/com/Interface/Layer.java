package com.Interface;

public interface Layer {
    public boolean send(Packet<?> packet);

    public boolean receive(Packet<?> packet);
}