public class Main3 {
    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";
        String topic = "test-topic";

        // Crear varios hilos de consumidores
        for (int i = 0; i < 3; i++) {
            Thread consumerThread = new Thread(new KafkaParallelConsumer(bootstrapServers, "group-1", topic), "Consumer-" + i);
            consumerThread.start();
        }
    }
}