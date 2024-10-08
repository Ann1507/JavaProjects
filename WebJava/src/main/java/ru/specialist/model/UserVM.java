package ru.specialist.model;

import org.springframework.stereotype.Component;

//Модель данных описывает структуру данных предметной области. 
//Состоит из набора свойств,описывающих данные и опционально логики обработки данных(активная модель)
// Задача: приветствовать пользователя по его имени.
//Решение: 
// Структура данных:
// Свойство username Имя пользователя(вводится пользователем и отображается на экране).Поэтому нужен get,set.
// Свойство hello (зависит от username) Отображается на экране поэтому нужен get.
@Component("uservm")
public class UserVM {
	private String userName;	
	private String prefix;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getHello() {//Привет, userName!
		if(getUserName()==null|| getUserName().isBlank()) 
			return "Привет!";
		else
			return String.format("Привет, %s %s!",getPrefix()==null?"":getPrefix(),getUserName());
	}
	
}
