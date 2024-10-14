package org.example;

// src/PublicadorConcreto.java
import java.util.ArrayList;
import java.util.List;

public class Publicador implements PublicadorInterface {
    private List<SuscriptorInterface> suscriptores = new ArrayList<>();

    @Override
    public void suscribir(SuscriptorInterface suscriptor) {
        suscriptores.add(suscriptor);
        System.out.println("Se ha suscrito: " + suscriptor.toString());
    }

    @Override
    public void desuscribir(SuscriptorInterface suscriptor) {
        if (suscriptores.remove(suscriptor)) {
            // Aquí añadimos el mensaje de desuscripción
            System.out.println("Se ha desuscrito: " + suscriptor.toString());
        } else {
            System.out.println("El suscriptor no se encontró en la lista.");
        }
    }

    @Override
    public void publicar(String mensaje) {
        for (SuscriptorInterface suscriptor : suscriptores) {
            suscriptor.actualizar(mensaje);
        }
    }
}
