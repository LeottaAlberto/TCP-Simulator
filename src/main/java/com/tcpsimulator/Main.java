package com.tcpsimulator;

import com.Hosts.Host;
import com.Hosts.Rete;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Rete network = new Rete();

        var host = new Host("PC-1", "192.168.12.1", "1A.2B.3C.4F", network);
        host.switchStatus();

        var host2 = new Host("PC-2", "192.168.12.2", "0A.1B.2C.3F", network);
        host2.switchStatus();

        network.connect(host);
        network.connect(host2);

        host.sendMessage("Ciao", host2.getNetwork().getIP());
    }
}