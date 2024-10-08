package ru.specialist;

import org.springframework.stereotype.Component;

@Component("myWindow")// Имя бина явно задается как параметр аннотации Component
// Если имя не задано в аннотации Component явно то в качестве имени бина использ.имя класса(с маленькой буквы)
public class PlasticWindow implements Window {
	public void open() {
		System.out.println("Open plastic window");
	}
}
