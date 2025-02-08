package ru.specialist;

import ru.specialist.dao.Course;
//Структура сообщений котор. будут обмениваться клиент и сервер
public class Message {
	//Команда серверу от клиента
	private String command;
	//курс котор клиент передает серверу
	private Course course;
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
