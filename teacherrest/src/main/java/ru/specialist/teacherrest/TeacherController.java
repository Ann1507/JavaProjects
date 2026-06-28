package ru.specialist.teacherrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {

	@Autowired
	private TeacherDao teacherdao;
	@GetMapping
	public List<Teacher> list(){
		return teacherdao.findAll();
	}
}
