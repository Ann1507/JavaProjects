package ru.specialist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

@Controller

public class CourseWSController {
	
	@Autowired
	private CourseDao courseDao;
	
	@MessageMapping("/courses")
	@SendTo("/course/all")
	public List<Course> course (@Payload Message message) {
		return courseDao.findAll();
	}

}
