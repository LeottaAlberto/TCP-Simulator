package com.Hosts;

import java.util.ArrayList;

import com.Interface.TransmissionChannel;
import com.Utility.Check;
import com.Utility.Generate;
import com.package_layer.Frame;

public class LAN implements TransmissionChannel {
    private static final String STANDARD_SUBNET_MASK = "255.255.255.0";

    private final ArrayList<Host> connected;
    private String IP;
    private String subnetMask;

    public LAN(String IP, String subnetMask) {
        this.connected = new ArrayList<>();
        this.IP = Check.checkIP_IPV4(IP) ? IP : Generate.nextIP();
        this.subnetMask = Check.checkSubnetMask(subnetMask) ? subnetMask : STANDARD_SUBNET_MASK;
    }

    public String getIP() {
        return this.IP;
    }

    public String getSubnetMask() {
        return this.subnetMask;
    }

    public void setIP(String IP) {
        if (!Check.checkIP_IPV4(IP)) return;

        this.IP = IP;
    }

    public void setSubnetMask(String subnetMask) {
        if (!Check.checkSubnetMask(subnetMask)) return;
        
        this.subnetMask = subnetMask;
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
            if (h.isStatus() && h.getNetwork().getMAC().equals(frame.getMACDest())) {
                return h.getNetwork().getPhysicsLayer().receive(frame);
            }
        }

        return false;
    }

    @Override
    public boolean sendBroadcast(Frame frame) {
        int count = 0;
        for (Host h : this.connected) {
            if (!h.isStatus())
                continue;

            if (!h.getNetwork().getMAC().equals(frame.getMACSrc())) {
                h.getNetwork().getPhysicsLayer().receive(frame);
            }
            count++;
        }

        System.out.println("Il frame è stato inviato a " + count + " host");

        return true;
    }
}