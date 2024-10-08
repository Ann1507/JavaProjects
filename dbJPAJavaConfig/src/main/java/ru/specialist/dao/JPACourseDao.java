package ru.specialist.dao;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository 
@Transactional//включение транзакции для каждого метода
public class JPACourseDao implements CourseDao{
	@PersistenceContext//Автоматически связывать это поле с объектом EntityManager созданным с пом.EntityManagerFactory(emf,которую сконыфигур.в DAOConfig)
	private EntityManager em;
	@Override
	public List<Course> findAll() {

		return em.createQuery("select c from Course c",//запрос на языке JPQL источник данных-множество объектов сущностей(в данн.случае курс
			Course.class).getResultList();//Выпонить JPQL запрос и получить  резульаты в виде JPQL сущности
				
	}

	@Override
	public Course findById(int id) {
		
		return em.find(Course.class,id);
	}

	@Override
	public List<Course> findByTitle(String search) {
		
		return em.createQuery("select c from Course c where c.title like :title",//Создаем JPQL запрос с параметром title 
				Course.class).//указываем тип результата это курс
				setParameter("title","%"+search+"%").//задаем значение параметра title 
				getResultList(); //выполняем jpql запрос получая коллекцию курсов
	}
	@Override
	public List<Course> findByLength(int length) {
		
		return em.createQuery("select c from Course c where c.length= :length",//Создаем JPQL запрос с параметром title 
				Course.class).
				setParameter("length",length). 
				getResultList();
		
	}
	@Override
	public void insert(Course course) {
		em.persist(course);//добавляет новый курс в БД
		
	}

	@Override
	public void update(Course course) {
		em.merge(course);//обновить (объединить) курс в БД
		
	}

	@Override
	public void delete(int id) {
		em.remove(findById(id));//удалить курс из базы данных
		
	}
	
}
// Сделать метод findByLength(int length)котор.возвпащ.список курсов указанн.длительности(по аналогии с findByTitle)
//Сначала заголовок этого метода добавить в интерфейс CourseDao затем реализовать его в JpaCourseDao и протестировать в App