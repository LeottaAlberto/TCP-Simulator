package com.Interface;

public interface Packet<T> {
    T getPayload();
    @Override
    String toString();
}