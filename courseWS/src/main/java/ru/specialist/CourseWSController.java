package ru.specialist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

@Controller //контроллер обрабатывает запросы клиента

public class CourseWSController {
	
	@Autowired //автоматическая инициализация поля с ссылкой на репозиторий для работы с бд
	private CourseDao courseDao;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;//шаблон для отправки сообщений клиенту
	
	@MessageMapping("/courses")//адрес, запросы по кот будут отправляться в этот метод
	//@SendTo("/course/all") // имя очереди,куда будут отправляться ответы на клиента
	public void processMessage (@Payload Message message) {
		// с помощью шаблона формир ответное сообщение клиенту включ. в себя список всех курсов,который получили с пом.репозитория
		messagingTemplate.convertAndSendToUser(message.getId(), "/course/all",courseDao.findAll());
	//											"/course/0/course/all"
	}

}
