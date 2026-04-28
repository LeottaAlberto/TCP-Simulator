package com.Interface;

public interface PDU<T> {
    T getPayload();
    @Override
    String toString();
}