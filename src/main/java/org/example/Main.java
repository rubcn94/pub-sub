package org.example;

import org.example.QueuePublisher;
import org.example.SuscriptorInterface;
import org.example.QueueSubscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Crear el Publisher
        QueuePublisher publisher = new QueuePublisher();

        // Crear varios suscriptores
        SuscriptorInterface subscriber1 = new QueueSubscriber("Subscriber 1");
        SuscriptorInterface subscriber2 = new QueueSubscriber("Subscriber 2");
        SuscriptorInterface subscriber3 = new QueueSubscriber("Subscriber 3");

        // Suscribir los suscriptores al Publisher
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);

        // Publicar varios mensajes
        publisher.pub("Message 1");
        publisher.pub("Message 2");
        publisher.pub("Message 3");
        publisher.pub("Message 4");

        // Esperar un poco para que los suscriptores procesen los mensajes
        Thread.sleep(5000);

        // Desuscribir a uno de los suscriptores
        publisher.unsubscribe(subscriber2);

        // Publicar más mensajes
        publisher.pub("Message 5");
        publisher.pub("Message 6");

        // Esperar un poco más para que se procesen los mensajes
        Thread.sleep(5000);
    }
}
