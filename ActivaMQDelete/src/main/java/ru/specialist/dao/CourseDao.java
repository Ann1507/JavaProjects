package ru.specialist.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
//Интерфейс кот.описывает функционал репозитория
//Репозиторий это объект кот.реализует(инкапсулирует)функционал доступа к БД
//Обычно состои из методов загрузки данных(в т.ч. по различным условиям
//и методов реализующих CRUD операции
public interface CourseDao extends CrudRepository<Course,Integer>{
				

}
