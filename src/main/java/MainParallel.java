public class MainParallel {
    public static void main(String[] args) throws InterruptedException {
        // Configuración del servidor Kafka
        String bootstrapServers = "localhost:9092";
        String topic = "parallel-topic";

        // Crear el Publisher (Productor)
        KafkaPublisher publisher = new KafkaPublisher(bootstrapServers);

        // Crear varios hilos de consumidores
        for (int i = 0; i < 3; i++) {
            Thread consumerThread = new Thread(new KafkaParallelConsumer(bootstrapServers, "group-parallel", topic), "Consumer-" + i);
            consumerThread.start();
        }

        // Publicar mensajes en Kafka
        for (int i = 1; i <= 5; i++) {
            publisher.publish(topic, "Mensaje " + i);
            Thread.sleep(1000);  // Simular tiempo entre publicaciones
        }

        // Cerrar el Publisher
        publisher.close();
    }
}
