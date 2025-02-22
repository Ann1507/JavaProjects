package ru.specialist;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class App {

	public static void main(String[] args) {
		Course course = new Course("Основы java", 33 ,"Введение в программирование на java");
		var course2 = new Course("ООП java", 40, "Объектно-ориентированнное программирование на java");
		var course3 = new Course("Описание курса", 35, "Описание курса java");
		/*List<Course> courses = new ArrayList<Course>();
		courses.add(course);
		courses.add(course2);
		courses.add(course3);*/
		/*List<Course> courses = List.of(course,course2,course3);*/
		List<Course> courses = new ArrayList<Course>(List.of(course,course2,course3));
		Collections.sort(courses);
		//courses.sort(null);
		for(Course c:courses)
			System.out.println(c);
		
	}

}
