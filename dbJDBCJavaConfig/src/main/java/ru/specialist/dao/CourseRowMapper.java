package ru.specialist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<Course>{

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course=new Course();
		course.setId(rs.getInt("id"));
		course.setTitle(rs.getString("title"));
		course.setLength(rs.getInt("length"));
		course.setDescription(rs.getString("description"));
		return null;
	}
	
}
