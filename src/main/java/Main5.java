public class Main5 {
    public static void main(String[] args) throws InterruptedException {
        // Configuración del servidor Kafka
        String bootstrapServers = "localhost:9092";
        String topic = "retry-topic";

        // Crear el Publisher (Productor)
        KafkaPublisher publisher = new KafkaPublisher(bootstrapServers);

        // Crear el Subscriber con reintentos automáticos
        KafkaSubscriber subscriber = new KafkaSubscriber(bootstrapServers, "group-retry", topic);

        // Crear un hilo para consumir mensajes de Kafka
        Thread subscriberThread = new Thread(() -> subscriber.consume());
        subscriberThread.start();

        // Publicar mensajes en Kafka
        for (int i = 1; i <= 5; i++) {
            publisher.publish(topic, "Mensaje " + i);
            Thread.sleep(1000);  // Simular tiempo entre publicaciones
        }

        // Cerrar el Publisher y Subscriber
        publisher.close();
        subscriber.close();
    }
}
