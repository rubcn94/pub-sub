package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        // Crear una cola bloqueante
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

        // Crear un hilo que espera por un mensaje en la cola
        Thread workerThread = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Esperando por mensajes...");
                    String message = messageQueue.take();  // Esto bloquea hasta que haya un mensaje
                    System.out.println("Recibido: " + message);
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido. Terminando el trabajo...");
                Thread.currentThread().interrupt();  // Restaurar el estado de interrupción
            }
        });

        // Iniciar el hilo trabajador
        workerThread.start();

        // Esperar un poco y luego interrumpir el hilo
        Thread.sleep(2000);
        System.out.println("Interrumpiendo el hilo trabajador...");
        workerThread.interrupt();  // Esto causará una InterruptedException en el workerThread

        // Esperar a que el hilo trabajador termine
        workerThread.join();

        System.out.println("Programa finalizado.");
    }
}
