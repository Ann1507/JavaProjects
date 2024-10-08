package ru.specialist.activemqclient;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;

public class App {
	
	private static final String brokerURL="tcp://localhost:61616";//адрес сервера очередей сообщения
	private static ConnectionFactory factory;
	private static Connection connection;
	private static Session session;
	
	public App() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);//создаем фабрику соединений с сервером очередей сообщений
		connection = factory.createConnection();//создаем соединение с сервером очер.сообщ.
		connection.start();// открываем соединение
		session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//открываем сессию
	} 
	
	public static void main(String[] args) throws JMSException {
		App client = new App();
		client.run();
	}
	
	public void run() throws JMSException {
		Destination topic = session.createTopic("POST");
		MessageConsumer messageConsumer = session.createConsumer(topic);
		messageConsumer.setMessageListener(message ->{
			if(message instanceof TextMessage) {
				try {
					System.out.println( ((TextMessage)message).getText() );
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
