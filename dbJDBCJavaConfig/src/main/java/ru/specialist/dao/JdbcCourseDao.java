package ru.specialist.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
@Repository//Аннотация,описывающая реализацию репозитория
//Эквивалентна аннотации Component,но с добавлением доп.функционала:
//Преобразовании исключений jdbc в исключение spring 
//Репозиторий инкапсулирует все взаимодействие с БД
//содерж.все необходимые sql запросы для работы с БД
public class JdbcCourseDao implements CourseDao {// CourseDao является реализацией слоя отображения реляционных данных,представленных в таблицах БД в объекты
	private static final String SQL_SELECT_COURSE=
			"select id,title,length,description from courses";
	private static final String SQL_SELECT_COURSE_BY_ID=
			SQL_SELECT_COURSE + " where id=?";
	private static final String SQL_SELECT_COURSE_BY_SEARCH=
			SQL_SELECT_COURSE + " where title like ?";
	private static final String SQL_DELETE_COURSE_BY_ID="Delete from courses where id= ?";
	private static final String SQL_UPDATE_COURSE="Update courses set title=?, length=?, description=? where id=?";
	private static final String SQL_INSERT_COURSE="Insert into courses (title,length,description) values (?,?,?)";
	@Autowired//Автоматическое связывание 
	private JdbcTemplate jdbcTemplate;//jdbcTemplate выполняет большую часть операций необходимых для выполнения sql запросов
	@Override
	public List<Course> findAll() {
		
		return jdbcTemplate.query(SQL_SELECT_COURSE, new BeanPropertyRowMapper(Course.class));
	//запросить запись списка в виде строк из базы данных и преобразовать строку в объект
		//с помощью средства отображения строк.
	}
	
	@Override
	public Course findById(int id) {
		
		return (Course)(jdbcTemplate.queryForObject(SQL_SELECT_COURSE_BY_ID,
				new Object[] {id},new CourseRowMapper()));
	}
	public List<Course> findByTitle(String search){
		return (List<Course>)(jdbcTemplate.query(SQL_SELECT_COURSE_BY_SEARCH,new Object[] {"%"+search+"%"}, new BeanPropertyRowMapper(Course.class)));
		
	}

	@Override
	public void insert(Course course) {
		PreparedStatementCreatorFactory f =
				new PreparedStatementCreatorFactory(SQL_INSERT_COURSE,
					Types.NVARCHAR, Types.INTEGER, Types.NVARCHAR);
			
			f.setGeneratedKeysColumnNames("id");
			KeyHolder kh = new GeneratedKeyHolder();
			
			jdbcTemplate.update(
				f.newPreparedStatementCreator(new Object[] {
					course.getTitle(), course.getLength(), course.getDescription()}),
				kh);
			
			course.setId(kh.getKey().intValue());		
	}

	@Override
	public void update(Course course) {
		jdbcTemplate.update(SQL_UPDATE_COURSE,course.getTitle(),course.getLength(),course.getDescription(),course.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_COURSE_BY_ID, id);
		
	}
	
	
	
}
