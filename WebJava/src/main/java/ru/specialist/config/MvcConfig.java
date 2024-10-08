package ru.specialist.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc// необходимо для конфигурации диспетчерсервлета(поддержка шаблона MVC)
@ComponentScan(basePackages= {"ru.specialist.controllers","ru.specialist.model"})// указывает пакет где искать классы контроллеров
@PropertySource("/WEB-INF/queue.properties")
/*
 
 Специфич.конф-ия для диспетчерсервлета котор описывает конфигур-ию бинов реализующ.шаблон MVC
 щаблон MVC:
 архитектрурный шаблон(паттерн) кот.описывает как создавать ту часть приложения кот.отвечает за пользовательский интерфейс
 //этот шаблон подразумевает что в программе в явном виде выделяют три вида сущностей:
  * Model(модель)-описание структур предметной области (множество классов java с набором свойств )
  * View (представление)-внешний вид страницы, обычно шаблон на сонове html (например,jsp страница)+ 
  * движок рендеринга,котор.на сонове этого шаблона сформирует конечный html,отправляемый клиеенту в качестве ответа
  * Controller (контроллер) связующее звено м/у моделями и представлениями. 
  * Класс java состояший из набора методов (actions-действия),кот.будут реагировать на запросы клиента(действия пользователя)
  * Обычно класс контроллера отмечается анннотацией @Controller
  * Также в классе контроллера использ.аннотация RequestMapping
  *  Метод контроллера возвращаеит имя jsp сраницы котор.будет передаваться движку рендеринга который на ее основе создаст конечный html 
 */
public class MvcConfig implements WebMvcConfigurer{
	
	//Это бин движка рендеринга,кот. на онове html страниц будет создавать конечный html
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setOrder(1);
		//добавляем префикс к имени jsp страницы(папка где будут лежать jspстраницы)
		viewResolver.setPrefix("/WEB-INF/views/");
		//добавляем суффикс к имени jsp страницы(расширение файла)
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(getJsonConverter());
	}
	@Bean(name="jacksonHttpMessageConverter")
	public MappingJackson2HttpMessageConverter getJsonConverter() {
		MappingJackson2HttpMessageConverter converter =new MappingJackson2HttpMessageConverter();
		converter.setPrettyPrint(true);
		return converter;
	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/*").addResourceLocations("/resources/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
