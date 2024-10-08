package ru.specialist.dbJdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration//Указывает что этот класс описывает конфигурацию бинов в виде методов
@PropertySource("jdbc.properties")//указывает из какого properties читать параметры
@ComponentScan("ru.specialist.dao")//указывает в каких пакетах искать бины, отмеченные аннотацией спринга
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
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(webDataSource());//jdbctemplate будет использоваться для выполнения  запросов (в реализации репозитория)
	}
}
