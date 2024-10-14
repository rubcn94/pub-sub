package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueuePublisher implements PublisherInterface {
    private List<SuscriptorInterface> subscribers = new ArrayList<>();

    private BlockingDeque<String> messageQueue = new LinkedBlockingDeque<>();

    public QueuePublisher() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = messageQueue.take();
                    notifySubscribers(message);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    @Override
    public void subscribe(SuscriptorInterface subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(SuscriptorInterface subscriber) {
        subscribers.remove(subscriber);
        System.out.println(subscriber.toString() + " se ha desuscrito.");

    }

    @Override
    public void pub(String message) {  // Cambiado de publish a pub
        try {
            // Add the message to the queue to be processed
            messageQueue.put(message);  // This can block if the queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Internal method to notify all subscribers with the message
    private void notifySubscribers(String message) {
        for (SuscriptorInterface subscriber : subscribers) {
            // Call the update method of each subscriber
            subscriber.actualizar(message);
        }
    }
}
