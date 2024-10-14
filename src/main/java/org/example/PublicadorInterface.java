package org.example;

public interface PublicadorInterface {
    void suscribir(SuscriptorInterface suscriptor);
    void desuscribir(SuscriptorInterface suscriptor);
    void publicar(String mensaje);
}