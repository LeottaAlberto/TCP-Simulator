package com.layer;

import javax.naming.Context;

import com.Interface.Layer;

public class TransportLayer implements Layer<String, String> {
    private int port;

    @Override
    public Object getPayload() {
        return null;
    }

    @Override
    public void send(String data, Context ctx) {
        // this.nextLayer.send(data, null);
    }

    @Override
    public void receive(String data) {
        // this.host.dataDecapsulation(data);
    }

    @Override
    public void send(String data, Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }
}