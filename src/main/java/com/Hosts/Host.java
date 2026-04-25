package com.Hosts;

import java.util.UUID;

import com.Interface.NetDevice;

public class Host implements NetDevice {
    public static final int MAX_HOST_NAME_LENGTH = 20;

    private String name;
    private boolean status;
    private NetworkCard network;

    /**
     * Normal constructor
     *
     * @param name hostname must not be empty or longer than
     *             {@code MAX_HOST_NAME_LENGTH}
     * @param ip   is a string like this 192.168.100.234
     * @param mac  is a string like this E4-60-17-3A-6F-6E
     */
    public Host(String name, String ip, String mac) {
        this.name = (name.isEmpty() || name.length() > 20)
                ? "host-" + UUID.randomUUID()
                : name;
        this.network = new NetworkCard(this, ip, mac);
    }

    /**
     * Copy constructor
     * 
     * @param ref another Host
     */
    public Host(Host ref) {
        this.name = ref.getName();
        this.network = new NetworkCard(ref, ref.getNetwork().getIP(), ref.getNetwork().getMAC());
    }

    // Getter

    public String getName() {
        return this.name;
    }

    public boolean isStatus() {
        return this.status;
    }

    public NetworkCard getNetwork() {
        return this.network;
    }

    // Setter

    /**
     * change hostname. The hostname must not be empty or longer than
     * MAX_HOST_NAME_LENGTH
     * 
     * @param name hostname
     */
    public void setName(String name) {
        if (name.isEmpty() || name.length() > 20)
            return;

        this.name = name;
    }

    /**
     * Turn off or turn on the host
     */
    public void switchStatus() {
        this.status = !this.status;
    }

    /**
     * Swap the network card for another, but only the host is off
     * 
     * @param network network card
     */
    public void setNetwork(NetworkCard network) {
        if (this.status)
            return;

        this.network = network;
    }

    @Override
    public void onReceiveData(String hostname, String data) {
        System.out.println(this.name
                + " received by: "
                + hostname
                + " this data: "
                + data);
    }
}
