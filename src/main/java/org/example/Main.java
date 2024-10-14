package org.example;

// src/Main.java
public class Main {
    public static void main(String[] args) {
        Publicador publicador = new Publicador();

        Suscriptor suscriptor1 = new Suscriptor("Suscriptor 1");
        Suscriptor suscriptor2 = new Suscriptor("Suscriptor 2");

        publicador.suscribir(suscriptor1);
        publicador.suscribir(suscriptor2);

        publicador.publicar("Mensaje de prueba");

        publicador.desuscribir(suscriptor1);

        publicador.publicar("Otro mensaje de prueba");
    }
}

