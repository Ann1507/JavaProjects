package ru.specialist;

public class Course {
	private String title;
	private int length;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Course(String title,int length, String description ) {
		this.title=title;
		this.length = length;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("%-20s %3d %s",getTitle(),getLength(),getDescription());
	}
	
}
