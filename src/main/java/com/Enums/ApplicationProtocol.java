package com.Enums;

public enum ApplicationProtocol {
    HTTP(80),
    HTTPS(443),
    SMTP(25);

    private final int defaultPort;

    ApplicationProtocol(int port) {
        this.defaultPort = port;
    }

    public int getPort() {
        return defaultPort;
    }
}
