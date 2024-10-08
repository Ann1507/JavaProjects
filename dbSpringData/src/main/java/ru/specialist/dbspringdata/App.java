package ru.specialist.dbspringdata;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;
import ru.specialist.dao.Teacher;
import ru.specialist.dao.TeacherDao;
import ru.specialist.services.ReportService;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
public class App {

	public static void main(String[] args) {
		// Создается контейнер спринга в соответсвии с конфигурацией класса DaoConfig
		 ApplicationContext context=new AnnotationConfigApplicationContext(DaoConfig.class);
		 //Получаем ссылку на реализацию репозитория по типу интерфейса CourceDao
		 CourseDao dao=context.getBean(CourseDao.class);
		 Optional<Course> c=dao.findById(55);
		 TeacherDao dao1=context.getBean(TeacherDao.class);
		 //Teacher teachers1=new Teacher();
		 if(c.isPresent()) {
			Course course=c.get();
			course.setLength(course.getLength()+2);
			dao.save(course);
		 }
		 else
			 System.out.println("Course id=55 не найден");
		 for(Course course1:dao.findAll())
			System.out.println(course1);
		 /*for(Teacher teachers1:dao1.findAll())
			 System.out.println(teachers1);*/
		 
		 for(Teacher teachers1:dao1.findByName("Fedotov"))
			  System.out.println(teachers1);
		 ReportService rp=context.getBean(ReportService.class);
		 double average=rp.getAverageCourseLength();
		 System.out.println(average);
		 /*Teacher teacher=new Teacher();
		 teacher.setName("Fedotov");
		 teacher.setAddr("Moscow");
		 teacher.setPhone("8564715");
		 System.out.println(teacher);
		 dao1.save(teacher);*/
		 double count=rp.coursesoutofshandlon();
		  System.out.println(count);
		 

	}

}
//Сделать класс сущности Teacher повтопяющий структуру таблицы teahers(по аналогии с курсами)
// Сделать репозиторий TeacherDao,используя спринг дата (по аналогии с CourseDao).
// Вывести список преподавателей с app используя teacherDao
// Сделать метод findByName в репозитории TeacherDao и проверить его работу.
//Слздать в репозитории метод List<Course> findByTitle(String search),которфй возвращает коллекцию курсов в названии которых есть указанная строка поиска
//search Для реализации в sql запросе добавить блок where like(%search%).Для выполнения такого запроса с парметром использовать метод 
//jdbcTemplate.query(с параметр queryForObject).
//Сделать метод кот.считает длительность всех курсов за исключением самого короткого и самого длинного.