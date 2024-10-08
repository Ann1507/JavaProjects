package ru.specialist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component//Аннотация Compontent указывает спрингу, что на основе этого класса будет создана компонента(Bean)-объект этого класса которым
//будет управлять контейнер спринга
//@Lazy//Это ленивая инициализация бина-экземпляр бина(объекта) будет создаваться только при первом обращении к этому бину,а не при создании контекста(по умолчанию)
//По умолчанию (без аннотации Lazy) бин создается при создании контекста(контейнера спринга)
//@Scope("singleton")// Scope=singleton(по умолчанию) означает что в контейнере будет создаваться не более одного объекта этого бина независимо от того сколько раз мы запрашиваем этот бин
//(если бин будет запрашиваться многократно,то будет возвращаться ссылка на один и тот же объект
@Scope("prototype")//каждый раз когда запрашивается экземпляр бина будет создаваться новый объект
//(при многократных запросах будет создано множество объектов этого бина)
//При Scope=prototype всегда используется только ленивая инициализация т.е. объект создается только в момент обращения к нему и не создается заранее
//(вне зависимости от наличия аннотации Lazy)
//https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html 
//Следующие варианты scope только для сервисов и веб приложений:
//request-один экземпляр бина на обработку одного запроса(для обработки след запроса будет создан новый экземпляр)
// session- один экземпляр бина для обработки множества запросов одного клиента (для каждого клиента создается собственный экземпляр бина)
// application- один экземпляр бина на один ServletContext (ServletContext это конфигурация сервлета а сервлет это объект обрабатывающий запросы)
//(в рамках веб приложения может быть несколько сервлетов а значит несколько сервлетконтекстов)
//websocket-один экземпляр бина на одно соединение по протоколу вебсокет
//протокол вебсокет(в отличии от http) реализует асинхронный(в режиме онлайн не ждем когда дойдет сообщение) двунаправленный обмен сообщениями м/у клиентом и сервером.
public class House {
	private Window window;
	private int height;
	private Door mainDoor;
	public Door getMainDoor() {
		return mainDoor;
	}
	@Value("#{woodDoor}")//Указывает значение,которое булет использовано контейнером спринга для инициализации этого свойства
	// при создании этого бина.Здесь происх.обращение к бину с именем woodDoor.
	//#{} означает что внутри скобок будет записано выражение спринг(spel-spring expresion)
	//в spel идентификатор означает имя бина(если бину не указано имя,то имя класса с маленькой буквы)
	public void setMainDoor(Door mainDoor) {
		this.mainDoor=mainDoor;
	}
	
	public int getHeight() {
		return height;
	}
	@Value("${house.height}")
	public void setHeight(int height) {
		this.height = height;
	}
	public Window getWindow() {
		return window;	
	}
	@Autowired
	//@Value("#{myWindow}")//Связывание через свойство
	public void setWindow(Window window) {
		this.window=window;
	}
	public void ventilate() {
		getWindow().open();
	}
	public void installDoors() {
		getMainDoor().install();
	}
	public House(@Value("#{myWindow}") Window window) {//связывание через параметры контруктора
		System.out.println("House constructor");
		this.window=window;
	}
	
	
}
