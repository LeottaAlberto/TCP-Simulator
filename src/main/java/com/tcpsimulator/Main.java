package com.tcpsimulator;

import com.Hosts.Host;
import com.Hosts.Rete;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inizializzazione del simulatore TCP/IP");

        Rete network = new Rete();

        Host host = new Host("PC-1", "192.168.12.1", "4A:1B:C3:94:5E:7D", network);
        host.switchStatus();

        Host host2 = new Host("PC-2", "192.168.12.2", "82:F9:12:0B:AC:34", network);
        host2.switchStatus();

        network.connect(host);
        network.connect(host2);

        host.sendMessage("Ciao", host2.getNetwork().getIP());
    }
}