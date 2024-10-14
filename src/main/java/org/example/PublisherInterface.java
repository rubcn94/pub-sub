package org.example;

public interface PublisherInterface {
    void subscribe(SuscriptorInterface suscriptor);
    void unsubscribe (SuscriptorInterface suscriptor);
    void pub (String mensaje);
}