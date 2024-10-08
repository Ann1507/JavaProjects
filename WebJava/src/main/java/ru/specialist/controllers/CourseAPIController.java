package ru.specialist.controllers;

import java.util.List;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
/*
 Команды протокла http отпределяют какой метод сервиса следует выполнить на сервере.
 api/course GET-получить список сущностей
 api/course/{id} GET-получить отдельную сушность по id(id переменная часть)
 api/course POST-добавление новой сущности
 api/course/{id} PUT- обновление сущности
 api/course/{id} DELETE-удаление сущности
  */
@RestController
@RequestMapping("api/course")
public class CourseAPIController {
	
	@Autowired
	private CourseDao courseDao;
	
	
	@RequestMapping(method=RequestMethod.GET)//api/course
	public List<Course> list() {
		return courseDao.findAll();
	}
	 
	@RequestMapping(method=RequestMethod.GET,value="/{id}")//api/course/{id}
	public Course findById(@PathVariable("id") int id) {
		 return courseDao.findById(id).get();
		
	}
	
	@RequestMapping(method=RequestMethod.POST)//api/course
	public Course create(@RequestBody Course course) {
		return courseDao.save(course);
	}
	
	@Value("${brokerURL}")//обращение к коллекции параметров прочитанной из properties файлов
	private String brokerURL;
	@RequestMapping(value="/queue",method=RequestMethod.POST)//api/course/queue
	public Course saveUsingQueue(@RequestBody Course course) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);//создаем фабрику соединений с сервером очередей сообщений
		Connection connection = factory.createConnection();//создаем соединение с сервером очер.сообщ.
		connection.start();// открываем соединение
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//открываем сессию
		MessageProducer producer = session.createProducer(null);//создаем источник сообщения	
		Destination topic = session.createTopic("POST");// создаем топик(в рамках одного сервера можно создать множество независимых топиков(очередей сообщ)),ТЕСТ-название очереди
		Gson g=new GsonBuilder().setPrettyPrinting().create();//создаем объект котор.будет сериализовать наш курс
		String json=g.toJson(course);//сериализуем курс
		TextMessage msg = session.createTextMessage(json);//создаем сообщение(содержащее сериализованный курс)
		producer.send(topic,msg);//отправляем сообщение в конкр.топик
		connection.close();
		return course;
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/{id}")//api/course/{id}
	public Course update(@RequestBody Course course,@PathVariable("id") int id) {
		if(id!=course.getId()) throw new RuntimeException("Invalid id");
		return courseDao.save(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")//api/course/{id}
	public void delete(@PathVariable("id") int id) {
		courseDao.deleteById(id);
	}
	
	@RequestMapping(value="/queue/{id}",method=RequestMethod.DELETE)//api/course/queue
	public void deleteUsingQueue(@PathVariable("id") int id) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);//создаем фабрику соединений с сервером очередей сообщений
		Connection connection = factory.createConnection();//создаем соединение с сервером очер.сообщ.
		connection.start();// открываем соединение
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//открываем сессию
		MessageProducer producer = session.createProducer(null);//создаем источник сообщения	
		Destination topic = session.createTopic("DELETE");// создаем топик(в рамках одного сервера можно создать множество независимых топиков(очередей сообщ)),ТЕСТ-название очереди
		Course c= new Course();
		c.setId(id);
		Gson g=new GsonBuilder().setPrettyPrinting().create();//создаем объект котор.будет сериализовать наш курс
		String json=g.toJson(c);
		System.out.println(json);
		TextMessage msg = session.createTextMessage(json);//создаем сообщение(содержащее сериализованный курс)
		producer.send(topic,msg);//отправляем сообщение в конкр.топик
		connection.close();
		
	}
}
/*{
"id":"0",
"title":"Kafka",
"length":"16",
 " description":"kafka intro"
}
*/
//1 В классе CourseApi Controller реализовать метод put, delete
// для метода put вызвать метод save репозитория courseDao,для delete вызвать delete
//дляф метода put сделать два параметра id,course.для метода delete один параметр id.
// не забыть навесить аннотациидля методов(Path...)
//2 По аналогии сделать TeacherAPIController.