package com.package_layer;

import com.Enums.ApplicationProtocol;
import com.Interface.Packet;

public class Application implements Packet<Application> {
    private final String mess;
    private final ApplicationProtocol prot;

    public Application(String mess, ApplicationProtocol prot) {
        this.mess = mess;
        this.prot = prot;
    }

    public String getMess() {
        return this.mess;
    }

    public ApplicationProtocol getProt() {
        return this.prot;
    }

    @Override
    public Application getPayload() {
        return this;
    }

}
