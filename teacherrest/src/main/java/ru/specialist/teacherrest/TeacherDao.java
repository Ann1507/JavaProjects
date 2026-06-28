package ru.specialist.teacherrest;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TeacherDao extends CrudRepository<Teacher,Integer>{
	@Query("select t from Teacher t where t.name like :name")//Указывает spring data  что для реализации этого метода следует использовать данные jpql запроса
	List<Teacher> findByNameWithLike(@Param("name") String name);
	List<Teacher> findAll();
}
