package com.package_layer;

import com.Enums.ApplicationProtocol;
import com.Interface.PDU;

public class Application implements PDU<Application> {
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
    @Override
    public String toString() {
        return "Application{" +
                "mess='" + mess + '\'' +
                ", prot=" + prot +
                '}';    
    }
}
