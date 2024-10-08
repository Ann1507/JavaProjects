package ru.specialist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
//Вариант конфигурации javaConfig позволяет использовать классы,не размеченные никакими аннотациями(в тч.чужие классы)
// а задавая конфигурвцию с пом.методов отмеченных аннотацией Bean
@Configuration//этот класс описывает конфигурацию бинов с пом.методов
@PropertySource("house.properties")
public class BuilderConfig {
	//@Value("${house.height}")
	//private int height;
	@Autowired
	private Environment e;
	@Bean//аннотация задает конфигурацию бина
	//Имя бина задается как параметрт аннотации бин,если не задано то берется имя метода в качестве имени бина
	@Scope("prototype")
	public Window plasticWindow() {
		return new PlasticWindow();
	}
	@Bean("myWindow")
	@Scope("prototype")
	public Window woodWindow() {
		return new WoodWindow();
	}
	@Bean
	public Door metalDoor() {
		return new MetalDoor();
	}
	@Bean
	@Scope("prototype")
	public Door woodDoor() {
		return new WoodDoor();
	}
	@Bean
	@Lazy
	public House house() {
		House h=new House(woodWindow());
		h.setMainDoor(metalDoor());
		//h.setHeight(height);
		h.setHeight(e.getProperty("house.height",Integer.class,2));
		return h;
	}
	

}
