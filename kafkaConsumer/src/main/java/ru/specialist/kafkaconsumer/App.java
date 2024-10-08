package ru.specialist.kafkaconsumer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
public class App {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
		
		Consumer<String,String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("Newtopic"));
		while(true) {
			ConsumerRecords<String,String> messages = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String,String> message:messages)
				System.out.printf("Offset: %s, key: %s, value: %s\n", 
						message.offset(), message.key(), message.value());
			
		}
		//consumer.close();

	}

}
