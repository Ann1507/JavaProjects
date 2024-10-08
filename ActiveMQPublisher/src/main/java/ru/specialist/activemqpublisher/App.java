package ru.specialist.activemqpublisher;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class App {

	private static final String brokerURL="tcp://localhost:61616";//адрес сервера очередей сообщения
	private static ConnectionFactory factory;
	private static Connection connection;
	private static Session session;
	private static MessageProducer producer;
	
	public App() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);//создаем фабрику соединений с сервером очередей сообщений
		connection = factory.createConnection();//создаем соединение с сервером очер.сообщ.
		connection.start();// открываем соединение
		session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//открываем сессию
		producer = session.createProducer(null);//создаем источник сообщения
	}
	
	public void close() throws JMSException {
		if (connection != null) {
			connection.close();//закрываем соединение с сервером
			connection = null;
		}
	}
	
	
	
	public static void main(String[] args) throws JMSException {
		
		App publisher = new App();
		publisher.run();

	}
	
	public void run() throws JMSException {
		Destination topic = session.createTopic("TEST");// создаем топик(в рамках одного сервера можно создать множество независимых топиков(очередей сообщ)),ТЕСТ-название очереди
		TextMessage msg = session.createTextMessage("test message");//создаем сообщение
		producer.send(topic,msg);//отправляем сообщение в конкр.топик
		System.out.println(msg.getText());
		close();
	}

}
