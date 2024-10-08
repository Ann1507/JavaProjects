package ru.specialist.kafkaproducer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
public class App {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		Producer<String,String> producer = new KafkaProducer<>(props);
		ProducerRecord<String,String> message = new ProducerRecord<>("Newtopic","key","Hello");
		producer.send(message,(metadata,exception)->{
			if (exception!=null) 
				exception.printStackTrace();
			else 
				System.out.printf("message send to topic %s partition %s offset %s\n",
						metadata.topic(),metadata.partition(),metadata.offset());
		});
		producer.close();
	}

}
