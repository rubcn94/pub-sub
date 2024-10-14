package org.example;

public interface Subscriber {
    void onMessage(String message);
    String getName();
}
