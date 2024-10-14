package org.example;

public class QueueSubscriber implements SuscriptorInterface {
    private String name;

    public QueueSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void actualizar(String message) {
        System.out.println(name + " received message: " + message);
    }

    @Override
    public String toString() {
        return name;
    }
}
