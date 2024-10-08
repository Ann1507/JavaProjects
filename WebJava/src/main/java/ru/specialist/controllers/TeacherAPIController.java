package ru.specialist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.specialist.dao.Teacher;
import ru.specialist.dao.TeacherDao;

@RestController
@RequestMapping("api/teacher")

public class TeacherAPIController {
		
		@Autowired
		private TeacherDao teacherDao;
		
		
		@RequestMapping(method=RequestMethod.GET)
		List<Teacher> list() {
			return teacherDao.findAll();
		}
		 
		@RequestMapping(method=RequestMethod.GET,value="/{id}")
		public Teacher findById(@PathVariable("id") int id) {
			 return teacherDao.findById(id).get();
			
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public Teacher create(@RequestBody Teacher teacher) {
			return teacherDao.save(teacher);
		}
		
		@RequestMapping(method=RequestMethod.PUT,value="/{id}")
		public Teacher update(@RequestBody Teacher teacher,@PathVariable("id") int id) {
			if(id!=teacher.getId()) throw new RuntimeException("Invalid id");
			return teacherDao.save(teacher);
		}
		
		@RequestMapping(method=RequestMethod.DELETE,value="/{id}")//api/teacher/{id}
		public void delete(@PathVariable("id") int id) {
			teacherDao.deleteById(id);
		}
	}

