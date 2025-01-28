package ru.specialist;

import ru.specialist.dao.Course;

public class Message {
	private String command;
	private Course course;
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
