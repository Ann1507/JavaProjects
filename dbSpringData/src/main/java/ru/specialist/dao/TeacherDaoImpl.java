package ru.specialist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//имя этого класса = имя исходного интерфейса репозитория+Impl
public class TeacherDaoImpl implements TeacherDaoCustomized {
	
	@Autowired
	private TeacherDao t;
	
	@Override
	public List<Teacher> findByName(String name) {
		return t.findByNameWithLike("%"+name+"%");
	}

	
	
}
