package ru.specialist.kafkaproducer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
public class App {

	public static void main(String[] args) {
		Properties props = new Properties();//Создание коллекции настроек(key-value)
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");//адрес сервера кафки
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());//задаем параметр класс сериалиазотра ключа(объект преобр в последовательность байтов) для отправки сообщений в кафку
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());//задаем класс сериализатор значения
		
		Producer<String,String> producer = new KafkaProducer<>(props);//создаем объект кот будет отправлять сообщения кот будет отправлять сообщения в топик кафки с указанием коллекции параметров
		ProducerRecord<String,String> message = new ProducerRecord<>("Newtopic","key","Hello");//создаем сообщение(с указанием топика,ключа и значение)
		//отправляем сообщение
		producer.send(message,(metadata,exception)->{//функция (лямбда) кот будет вызвана когда сообщение отправлено
			if (exception!=null) //если была ошибка (exception)
				exception.printStackTrace();//напечатать подроб.инф.об ошибке
			else 
				System.out.printf("message send to topic %s partition %s offset %s\n",
						metadata.topic(),metadata.partition(),metadata.offset());//если не было ошибок при отправке сообщения из объекта метадата извлекаем название топика, партицию и смещение куда сообщение попало
		});
		producer.close();//закрываем продюсера объект
	}

}
//Перейти в папку кафки cd c:\kafka\bin\windows
//zookeeper-server-start.bat ..\..\config\zookeeper.properties запуск zookeper для управления кластером(множеством узлов кафки)
// открыть новую командную строчку и снова перйти в папку кафка cd c:\kafka\bin\windows
//выполнииь команду запуска севрвера кафки kafka-server-start.bat ..\..\config\server.properties
//в новой ком.строке команда создания нового топика kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic TestTopic
//в новом ком окнеcd c:\kafka\bin\windows
//команда просмотра списка топиков kafka-topics.bat --list --bootstrap-server localhost:9092