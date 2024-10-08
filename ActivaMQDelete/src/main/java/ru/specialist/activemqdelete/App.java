package ru.specialist.activemqdelete;

import org.apache.activemq.ActiveMQConnectionFactory;
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

import javax.jms.*;
@Component
public class App {
	@Value("${brokerURL}")
	private String brokerURL;//адрес сервера очередей сообщения
	private ConnectionFactory factory;
	private Connection connection;
	private Session session;
	private ApplicationContext context;
	@Autowired
	private CourseDao dao;
	
	
	
	public static void main(String[] args) throws JMSException {
		ApplicationContext context=new AnnotationConfigApplicationContext(DaoConfig.class);
		context.getBean(App.class).run();
	}
	
	public void run() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);//создаем фабрику соединений с сервером очередей сообщений
		connection = factory.createConnection();//создаем соединение с сервером очер.сообщ.
		connection.start();// открываем соединение
		session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//открываем сессию
		
		Gson gson=new GsonBuilder().create();
		Destination topic = session.createTopic("DELETE");
		MessageConsumer messageConsumer = session.createConsumer(topic);
		messageConsumer.setMessageListener(message ->{
			if(message instanceof TextMessage) {
				try {
					String json=((TextMessage)message).getText(); 
					Course c = gson.fromJson(json,Course.class);
					System.out.printf( "delete id %d\n", c.getId());
					dao.deleteById(c.getId());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
//Сделать удаление курса через очередь:
//1 на странице teacher .html сделать еще одну ссылку удалить через очередь,которая должна вызывать новый метод CourseAPIController
//2 Добавить новый метод в CourseApiController, кот.вставляет в топик delete сообещение сожержащее id курса кот.надо удалить
//3 Создать проект ActivaMQDelete кот слушает топик delete,олучает из этого топика id курса и удаляет его из базы