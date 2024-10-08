package ru.specialist.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration//Указывает что этот класс описывает конфигурацию бинов в виде методов
@PropertySource("jdbc.properties")
@PropertySource("queue.properties")//указывает из какого properties читать параметры
@ComponentScan(basePackages={"ru.specialist.dao","ru.specialist.activemqtodatabase"})//указывает в каких пакетах искать бины, отмеченные аннотацией спринга
@EnableTransactionManagement//включение поддрержки транзакций(необходимо для работы hibernate)
@EnableJpaRepositories(basePackages="ru.specialist.dao")//указывает модулю спринг дата в каких пакетах искать интерфейсы репозиториев котор.спринг дата должен автоматически реализовать
public class DaoConfig {
	
	@Autowired
	private Environment env;//стандарный сервис спринга для получения данных из properties файла
	
	@Bean
	public DataSource webDataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();//класс выполняет соединение к БД
		ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		ds.setUrl(env.getProperty("jdbc.url"));
		ds.setUsername(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		return ds;
	
	}
	@Bean("entityManagerFactory")
	 public LocalContainerEntityManagerFactoryBean emf() {//фабрика кот.создает объект EntityManager
		//через объекты EntityManager будем загружать данные из БД и сохранять данные в БД
		 LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
		 emf.setDataSource(webDataSource());
		 emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());//адаптер для работы с конкретной реализацией ORM(в данн.случае с Hibernate,есть др.реализации ORM например TopLink от Oracle)
		 emf.setPackagesToScan("ru.specialist.dao");
		 return emf;
	 }
	@Bean
	public PlatformTransactionManager transactionManager() {//объект кот управляет транзакциями
		return  new JpaTransactionManager(emf().getObject());
	}
	
}
