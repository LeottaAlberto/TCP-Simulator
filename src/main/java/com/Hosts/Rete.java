package com.Hosts;

import java.util.ArrayList;

import com.Interface.TransmissionChannel;
import com.package_layer.Frame;

public class Rete implements TransmissionChannel {
    private final ArrayList<Host> connected;

    public Rete() {
        this.connected = new ArrayList<>();
    }

    public void connect(Host device) {
        if (device == null)
            return;

        connected.add(device);

        System.out.println("Host " + device.getName() + " connesso alla rete correttamente");
    }

    public void disconnect(Host device) {
        connected.remove(connected.indexOf(device));
    }

    @Override
    public boolean sendOnWire(Frame frame) {
        for (Host h : this.connected) {
            if (h.getNetwork().getMAC().equals(frame.getMACDest())) {
                return h.getNetwork().getPhysicsLayer().receive(frame);
            }
        }

        return false;
    }

    @Override
    public boolean sendBroadcast(Frame frame) {
        for (Host h : this.connected) {
            if (!h.getNetwork().getMAC().equals(frame.getMACSrc())) {
                h.getNetwork().getPhysicsLayer().receive(frame);
            }
        }

        return false;
    }
}