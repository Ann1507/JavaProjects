package ru.specialist.kafkaclient;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;
import ru.specialist.dao.DaoConfig;
@Component
public class App {
	@Value("${kafkaServer}")
	private String kafkaServer;//адрес сервера очередей сообщения
	private ApplicationContext context;
	@Autowired
	private CourseDao dao;
	
	
	
	public static void main(String[] args)  {
		ApplicationContext context=new AnnotationConfigApplicationContext(DaoConfig.class);
		context.getBean(App.class).run();
	}
	
	public void run() {
		Properties props = new Properties();//создаем коллеекц.параметров
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");//добавл параметры адрес сервера
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());//добавляем для ключа класса десериализатора преобр из байт в объект
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());//добавляем для значения класса десериализатора преобр из байт в объект
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");//указание идетификатора группы конфигурации
		
		Consumer<String,String> consumer = new KafkaConsumer<>(props);//создаем получателя сообщений с указанием коллекции параметр.
		//подписка на получение сообщения из топика Newtopic
		consumer.subscribe(Collections.singletonList("POSTCourse"));		
		Gson gson=new GsonBuilder().create();
		
		while(true) {//бесконечный цикл
			//извлечь коллекцию сообщений из топик.
			ConsumerRecords<String,String> messages = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String,String> message:messages){ // перебираем коллекцию ссообщений
				System.out.printf("Offset: %s, key: %s, value: %s\n", 
						message.offset(), message.key(), message.value());//выводим информацию о сообщении
				String json=message.value();
				System.out.println( json );
				Course c = gson.fromJson(json,Course.class);
				dao.save(c);
			}
		}
		//consumer.close();
					
		
	}
	

}
