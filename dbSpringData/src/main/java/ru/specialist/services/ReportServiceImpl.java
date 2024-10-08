package ru.specialist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private CourseDao coursedao;

	@Override
	public double getAverageCourseLength() {
		// Задача: найти среднее арифметическое всех курсов
		// 1. Загрузить все курсы из Репозитория и поместить их в переменную courses
		// 2. Создать переменную для хранения суммы длительности курсов типа int с
		// начальным значением 0
		// 3. Перебираем в цикле курсы
		// 4. Для каждого курса извлекаем его длительность и складываем с суммой
		// длительности курсов
		// 5. Разделим сумму длительности курсов на количество курсов,предварительности
		// преобразовав сумму к типу double
		Iterable<Course> courses = coursedao.findAll();
		int summalength = 0;
		int count = 0;
		for (Course c : courses) {
			int lengthcourse = c.getLength();
			summalength = summalength + lengthcourse;
			count++;

		}
		return (double) summalength / count;

	}
	// 0.Загрузить все курсы из Репозитория и поместить их в переменную courses
	// 1.Определить длительность самого короткого курса и самого длинного
	// 2.Создать переменную для хранения короткого длительности курса и длинного
	// курса типа int;
	// 3. Перебираем в цикле курсы
	// 4. Для каждого курса извлекаем его длительность
	// 5.Исключить из списка самый кор и длинн.курсы

	public double coursesoutofshandlon() {
		Iterable<Course> courses = coursedao.findAll();
		int maxlength = 1;
		int summalength = 0;
		int count = 0;
		int minlength = Integer.MAX_VALUE;
		for (Course c : courses) {
			int lengthcourse = c.getLength();
			if (lengthcourse > maxlength)
				maxlength = lengthcourse;
			if (lengthcourse < minlength)
				minlength = lengthcourse;
		}
		for (Course c1 : courses) {
			if (minlength != c1.getLength() && maxlength != c1.getLength()) {
				summalength = summalength + c1.getLength();

				count++;
			}

		}

		return (double) (summalength / count);

	}
}

// 1 2 5 8
// 1+2=3 n=3 n+5=8 n=n+5 