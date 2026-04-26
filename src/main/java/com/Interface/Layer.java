package com.Interface;


public interface Layer {
    public void send(Packet packet);

    public void receive(Packet packet);
}