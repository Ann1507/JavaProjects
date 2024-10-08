package ru.specialist.dbJdbc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

import org.springframework.context.ApplicationContext;
public class App {

	public static void main(String[] args) {
		// Создается контейнер спринга в соответсвии с конфигурацией класса DaoConfig
		 ApplicationContext context=new AnnotationConfigApplicationContext(DaoConfig.class);
		 //Получаем ссылку на реализацию репозитория по типу интерфейса CourceDao
		 CourseDao dao=context.getBean(CourseDao.class);
		 //dao.delete(2);
		 /*Course course=dao.findById(5);
		 course.setLength(course.getLength()+1);
		 dao.update(course);*/
		 Course course=new Course();
		 course.setTitle("Spring");
		 course.setLength(40);
		 course.setDescription("Spring framework");
		 System.out.println(course);
		 dao.insert(course);
		 System.out.println(course);
		 for(Course course1:dao.findAll())
			 System.out.println(course1);
		 	 	 
		 //System.out.println(course);
		 //for(Course course:dao.findByTitle("web"))
			// System.out.println(course);
		 
	}

}
//Слздать в репозитории метод List<Course> findByTitle(String search),которфй возвращает коллекцию курсов в названии которых есть указанная строка поиска
//search Для реализации в sql запросе добавить блок where like(%search%).Для выполнения такого запроса с парметром использовать метод 
//jdbcTemplate.query(с параметр queryForObject).