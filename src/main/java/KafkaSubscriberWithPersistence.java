import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;5 erRecord;
import org.apache.kafka.clients.consumer.Consum erRecords;

public class KafkaSubscriberWithPersistence extends KafkaSubscriber {

    public KafkaSubscriberWithPersistence(String bootstrapServers, String groupId, String topic) {
        super(bootstrapServers, groupId, topic);
    }

    @Override
    public void consume() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                boolean processed = processRecord(record, 0);
                if (!processed) {
                    persistFailedMessage(record);
                }
            }
        }
    }

    private void persistFailedMessage(ConsumerRecord<String, String> record) {
        try (FileWriter writer = new FileWriter("failed_messages.log", true)) {
            writer.write("Failed message: " + record.value() + "\n");
        } catch (IOException e) {
            System.err.println("Error saving failed message: " + e.getMessage());
        }
    }
}
