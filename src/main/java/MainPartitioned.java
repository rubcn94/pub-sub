public class MainPartitioned {
    public static void main(String[] args) throws InterruptedException {
        // Configuración del servidor Kafka
        String bootstrapServers = "localhost:9092";
        String topic = "partitioned-topic";

        // Crear el Publisher (Productor)
        KafkaPublisher publisher = new KafkaPublisher(bootstrapServers);

        // Crear consumidores asignados a particiones específicas
        Thread partitionConsumer1 = new Thread(new KafkaPartitionedConsumer(bootstrapServers, "group-partitioned", topic, 0), "Partition-Consumer-0");
        Thread partitionConsumer2 = new Thread(new KafkaPartitionedConsumer(bootstrapServers, "group-partitioned", topic, 1), "Partition-Consumer-1");

        partitionConsumer1.start();
        partitionConsumer2.start();

        // Publicar mensajes en Kafka
        for (int i = 1; i <= 5; i++) {
            publisher.publish(topic, "Mensaje " + i);
            Thread.sleep(1000);  // Simular tiempo entre publicaciones
        }

        // Cerrar el Publisher
        publisher.close();
    }
}
