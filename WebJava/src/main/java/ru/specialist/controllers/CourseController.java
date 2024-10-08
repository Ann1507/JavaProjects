package ru.specialist.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseDao courseDao;
	//Запросы от браузера пришедшие по алресу /courses командой GET протокола http будут обрабатываться методом list
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		uiModel.addAttribute("courses",courseDao.findAll());
		return "courses/list";
		//for(Course course:courses)
		//	System.out.println(course);
	}
	//Запросы от браузера пришедшие по адресу /courses/delete/id командой GET будут обраб.методом delete
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id,Model uiModel) {
		Optional<Course> c = courseDao.findById(id);
		if(c.isPresent())
			courseDao.delete(c.get());
		return "redirect:/courses";
	}
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String editform(@PathVariable("id") int id,Model uiModel) {
		Optional<Course> c = courseDao.findById(id);
		if(c.isPresent()) 
			uiModel.addAttribute("course",c.get());
		return 	"courses/edit";	
	}
	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String save(Course course,BindingResult bindingResult, Model uiModel) {
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("course",course);
			uiModel.addAttribute("error","Неверные данные");
			return "courses/edit";
		}
		courseDao.save(course);
		return "redirect:/courses";
	}
	
}
