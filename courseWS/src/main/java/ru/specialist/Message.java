package ru.specialist;

import org.springframework.data.annotation.Id;

import ru.specialist.dao.Course;
//Структура сообщений котор. будут обмениваться клиент и сервер
public class Message {
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
