package com.tcpsimulator;

import com.Hosts.Host;
import com.Hosts.Rete;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inizializzazione del simulatore TCP/IP");

        Rete network = new Rete();

        Host host = new Host("PC-1", "192.168.12.1", "4A:1B:C3:94:5E:7D", network);
        /* host.switchStatus();

        Host host2 = new Host("PC-2", "192.168.12.2", "82:F9:12:0B:AC:34", network);
        host2.switchStatus();

        Host host3 = new Host("PC-3", "192.168.12.3", "82:F9:12:0B:AC:35", network);
        host2.switchStatus();

        network.connect(host);
        network.connect(host2); */

        Host[] hosts = new Host[100];

        for (int i = 0; i < hosts.length; i++) {
            hosts[i] = new Host("PC-" + (i + 3), "192.168.12." + (i + 1), "82:F9:12:0B:AC:" + (36 + i), network);
            if (Math.random() < 0.5) 
                hosts[i].switchStatus();
            network.connect(hosts[i]);
        }

        // host.sendMessage("Ciao", host2.getNetwork().getIP(), false);
        hosts[0].sendMessage("Ciao a tutti sono il PC-0", null, true);
    }
}