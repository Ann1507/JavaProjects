package ru.specialist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

@Controller //контроллер обрабатывает запросы

public class CourseWSController {
	
	@Autowired //автоматическая инициализация поля с ссылкой на репозиторий для работы с бд
	private CourseDao courseDao;
	
	@MessageMapping("/courses")//адрес, запросы по кот будут отправляться в этот метод
	@SendTo("/course/all") // имя очереди,куда будут отправляться ответы на клиента
	public List<Course> course (@Payload Message message) {
		return courseDao.findAll();
	}

}
