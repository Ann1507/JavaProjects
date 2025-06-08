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
		int minLength=Integer.MAX_VALUE;
		for(Course c:courses) {
			if (c.getLength()<minLength)
				minLength=c.getLength();
			
		}
		System.out.printf("Minimal course length: %d\n", minLength);
		List<Course> minLengthCourses= new ArrayList<Course>();
		for(Course c:courses) {
			if (c.getLength()==minLength)
				minLengthCourses.add(c);
		}
		System.out.println("Courses with minimal length: ");
		for(Course c:minLengthCourses)
			System.out.println(c);
		//1.Найти максим.длительность среди всех курсов(число int)
		//2.Создать новую коллекцию(пустую) куда мы будем потом добавлять все курсы длительность
		//который равна максимальной(найденной в п.1)
		// 3.Перебрать исходную коллекцию курсов и скопировать курс из исходной коллец.в новую,
		//если его длительность равна максимальной 
		//4. Напечататть курсы из новой коллекции
		
		//1.
		int maxLength=Integer.MIN_VALUE;
		for(Course c:courses)
			if(c.getLength()>maxLength)
				maxLength=c.getLength();
		System.out.printf("Maximum course length: %d\n", maxLength);
		//2.
		
		List<Course> maxLengthCourses=new ArrayList<Course>();
		//3.
		for(Course c:courses)
			if(c.getLength()==maxLength)
				maxLengthCourses.add(c);
		//4.
		for(Course c:maxLengthCourses)
			System.out.println(c);
				
		
		
		 
	}
	
}
//Напечатать курсы с максимально длительностью