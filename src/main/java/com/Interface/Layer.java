package com.Interface;

/**
 * @param I = Input data from upper layer
 * @param O = Output data from down layer
 */
public interface Layer {
    public void send(Packet packet);

    public void receive(Packet packet);
}
