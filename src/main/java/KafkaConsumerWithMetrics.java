import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerWithMetrics {
    private KafkaConsumer<String, String> consumer;
    private final Counter messageCounter;

    public KafkaConsumerWithMetrics(String bootstrapServers, String groupId, String topic, MeterRegistry registry) {
        messageCounter = Counter.builder("kafka.messages.consumed")
                .description("Number of Kafka messages consumed")
                .register(registry);

        // Configuración del consumidor de Kafka
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
    }

    public void consume() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                messageCounter.increment();
                System.out.println("Mensaje consumido: " + record.value());
            }
        }
    }

    public void close() {
        consumer.close();
    }
}
