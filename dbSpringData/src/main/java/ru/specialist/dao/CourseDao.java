package ru.specialist.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course,Integer>{
	List<Course> findByLength(int length);
	List<Course> findByTitle(String search);	
}
