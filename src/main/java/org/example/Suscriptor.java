package org.example;

// src/SuscriptorConcreto.java
public class Suscriptor implements SuscriptorInterface {
    private String nombre;

    public Suscriptor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println(nombre + " recibió: " + mensaje);
    }

    @Override
    public String toString() {
        return nombre;
    }
}


