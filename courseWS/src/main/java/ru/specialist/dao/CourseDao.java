package ru.specialist.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//Для хранения данных используем реляц.БД MySQL.
//Для работы с mySQL мы используем объектную надсройку  hibernate.
//Для этого сделали класс сущности Course, который отраж. структуру таблицы Courses.
// Для реализации доступа к данным мы использовали модуль SpringData кот. используя hibernate 
//создал нам DAO(Data Access Object). С пом.этого ДАо объекта мы можем извлекать данные, обновлять,добавлять новые, удалять
// Этот ДАО объект будет использован в Рест сервисе который будет предоставлять эти данные в формате json по протоколу http клиентам
// Рест сервис реализован с пом. модуля Spring Web MVC в виде Рест контроллера (CourseApiController)
// Клиентами этого рест сервиса могут быть различные приложения работающие по протоколу http с этим сервисом и получающие от него данные в формате json
//  Например мы реализовали клиента на java Script работающего внутри web страницы.
public interface CourseDao extends CrudRepository<Course,Integer>{
	List<Course> findByLength(int length);
	List<Course> findByTitle(String search);
	List<Course> findAll();
	
}
