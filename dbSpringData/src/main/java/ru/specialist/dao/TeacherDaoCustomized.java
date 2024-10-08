package ru.specialist.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface TeacherDaoCustomized {
	List<Teacher> findByName(String name);
}
