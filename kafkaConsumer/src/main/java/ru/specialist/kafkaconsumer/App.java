package ru.specialist.kafkaconsumer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
public class App {

	public static void main(String[] args) {
		Properties props = new Properties();//создаем коллеекц.параметров
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");//добавл параметры адрес сервера
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());//добавляем для ключа класса десериализатора преобр из байт в объект
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());//добавляем для значения класса десериализатора преобр из байт в объект
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");//указание идетификатора группы конфигурации
		
		Consumer<String,String> consumer = new KafkaConsumer<>(props);//создаем получателя сообщений с указанием коллекции параметр.
		//подписка на получение сообщения из топика Newtopic
		consumer.subscribe(Collections.singletonList("Newtopic"));
		while(true) {//бесконечный цикл
			//извлечь коллекцию сообщений из топик.
			ConsumerRecords<String,String> messages = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String,String> message:messages)// перебираем коллекцию ссообщений
				System.out.printf("Offset: %s, key: %s, value: %s\n", 
						message.offset(), message.key(), message.value());//выводим информацию о сообщении
			
		}
		//consumer.close();

	}

}
