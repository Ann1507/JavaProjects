package ru.specialist.config;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
/*
 Веб приложение работает под управлением веб сервера(Apache TomCat)
 Веб сервер при получении запросов передает этот запрос для обработки оперед.объектам нащего приложения
 Эти объекты(классы) называются сервлетами,т.к. реализуют одноименный интерфейс Сервлет из 
 библиотеки Jakarta
 Jakarta это бывший JavaEE-стандарный интерфейс для бизнес приложений в т.ч. веб приложений
 Если мы используем Спринг(модуль Спринг Web MVC) в нем есть готовый сервлет (DispatcherServlet)
 который умеет перенаправлять запросы к контроллерам(контроллеры из паттерна MVC)
 Контроллеры долджны быть сконфигурированы как бины в контейнере Спринга
 Для этого диспетчер с поСервлет должен быть сконфигурирован с помощью AnnotationConfigWebApplicationContext
 В одном веб приложении могут существовать несколько экземпляров ДиспетчерСервлета с разной конфигурацией(возможно частично совпадающей) 
 для того чтобы можно было обрабатывать запросы по разным адресам разными способами
 Поэтому мы должнв описать две конфигурации:
 Корневую-общую для всех экземпляров Диспетчер Сервлета
 Специфическую-для конкретн.экземпляра
 Т.о реальная конфигурация конкрет.экзепляра ДиспетчерСервлета будет получаться как сумма общей(корневой) и специфической 
 Для того чтобы TomCat правильно наши сконфигурированные сервлеты мы можем создать класс для инициализации нашего веб приложения
 как наследник абстрактного класса AbstractAnnotationConfigDispatcherServletInitializer
 В методах этого класса мы определяем:
 корневую конфигурацию,
 специфическую конфин-ю
 Экземпляры диспетчерсервлетов 
 отображение адресов запросов на экземпляры диспетчерсервлетов
 фильтры(обработчики запросов до и после диспетчерсервлетов например кодировка) сквозной функционал независимый от сервлетов
 отображение адресов запросов на фильтры
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	//Возвращает массив классов корневой конфигурации(для всех экземпляров диспетчерсервлетов)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[] {ApplicationConfig.class} ;
	}

	//возвращает массив классов специфич.конфигурации для каждого диспетчерсервлета
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] {MvcConfig.class} ;
	}

	//Возвращает массив адресов запросы на которые перенапраляются в этот сервлет
	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
			
	}
	
	// Создает и конфигурирует экземпляр/ы диспетчерсервлета
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//создаем экземпляр корневой конфигурации
		// важно что это не просто ApplicationContext а 
		// WebApplicationContext
		AnnotationConfigWebApplicationContext rootContext =
				new AnnotationConfigWebApplicationContext();
		// Добавляем класс с корневой конфигурацией 
		rootContext.register(ApplicationConfig.class);
		rootContext.register(DaoConfig.class);
		// Регистрируем загрузщик корневой конфигурации 
		servletContext.addListener(new ContextLoaderListener(rootContext));
		// создаем экземпляр специфичес.конфиг-ии(тоже WebApplicationContext)
		AnnotationConfigWebApplicationContext servletAppContext = 
				new AnnotationConfigWebApplicationContext();
		//Добавляем класс со специфичес.конфигурацией
		servletAppContext.register(MvcConfig.class);
		//создаем экземпляр диспетчерсервлета с указанием конфигурации
		DispatcherServlet dispatcherServlet=new DispatcherServlet(servletAppContext);
		// Добавляем обработчик по умолчанию для непойманных исключений(для получения стандарной страницы с исключением в случае возникновения такого исключения)
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		//регистрируем наш диспетчер сервлет в вебсервере
		ServletRegistration.Dynamic dispatcher =servletContext.addServlet("dispatcher", dispatcherServlet);
		//включаем автоматич.загрузку диспетчерсервлета при запуске вебприложения
		dispatcher.setLoadOnStartup(1);
		// добавляем диспетчерсервлету отображение адресов-запосы по всем адресам будут отправлятьмя на этот сервлет
		dispatcher.addMapping("/");
		// создаем фильтр кодировки (для принудительной кодировки utf 8)
		FilterRegistration.Dynamic encodingFilter = 
        		servletContext.addFilter("encoding-filter", 
        				new CharacterEncodingFilter());
        //указываем кодировку utf 8
		encodingFilter.setInitParameter("encoding", "UTF-8");
        //включаем принуд.переодирование (в utf 8)
		encodingFilter.setInitParameter("forceEncoding", "true");
        //добавляем фильтру отображение адресов(запросы по всем адресам будут проходить через этот фильтр)
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}
}
