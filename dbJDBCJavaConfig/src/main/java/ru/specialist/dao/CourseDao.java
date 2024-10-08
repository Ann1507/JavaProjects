package ru.specialist.dao;
import java.util.List;
//Интерфейс кот.описывает функционал репозитория
//Репозиторий это объект кот.реализует(инкапсулирует)функционал доступа к БД
//Обычно состои из методов загрузки данных(в т.ч. по различным условиям
//и методов реализующих CRUD операции
public interface CourseDao {//интерфейс,в котором  метод findAll() типизирован коллекц. для получения всех курсов из БД
	List<Course> findAll();//найти все курсы
	Course findById(int id);//найти курс по id
	List<Course> findByTitle(String search);
	//CRUD
	void insert(Course course);
	void update(Course course);
	void delete(int id);
			

}
