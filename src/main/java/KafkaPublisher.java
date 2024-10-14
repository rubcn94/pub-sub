import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaPublisher {
    private KafkaProducer<String, String> producer;

    public KafkaPublisher(String bootstrapServers) {
        // Configurar las propiedades de Kafka
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    public void publish(String topic, String message) {
        // Publicar un mensaje en el tópico de Kafka
        producer.send(new ProducerRecord<>(topic, message));
        System.out.println("Mensaje enviado a Kafka: " + message);
    }

    public void close() {
        producer.close();
    }
}
