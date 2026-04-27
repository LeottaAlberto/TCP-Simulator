package com.Interface;

import com.package_layer.Frame;

public interface TransmissionChannel {
    void sendOnWire(Frame frame);

    void sendBroadcast(Frame frame);
}
