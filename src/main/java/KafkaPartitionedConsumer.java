import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaPartitionedConsumer {
    private KafkaConsumer<String, String> consumer;

    public KafkaPartitionedConsumer(String bootstrapServers, String groupId, String topic, int partition) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        TopicPartition partitionToConsume = new TopicPartition(topic, partition);
        consumer.assign(Arrays.asList(partitionToConsume));
    }

    public void consume() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Consumiendo de la partición " + record.partition() + ": " + record.value());
            }
        }
    }

    public void close() {
        consumer.close();
    }
}
