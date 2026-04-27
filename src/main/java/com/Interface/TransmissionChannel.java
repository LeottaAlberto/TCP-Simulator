package com.Interface;

import com.package_layer.Frame;

public interface TransmissionChannel {
    boolean sendOnWire(Frame frame);

    boolean sendBroadcast(Frame frame);
}
