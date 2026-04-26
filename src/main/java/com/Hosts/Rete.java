package com.Hosts;

import java.util.ArrayList;


public class Rete{
    private final ArrayList<Host> connected;

    public Rete(){
        this.connected = new ArrayList<>();
    }

    public void connect(Host device){
        connected.add(device);
    }

    public void disconnect(Host device){
        connected.remove(connected.indexOf(device));
    }
}