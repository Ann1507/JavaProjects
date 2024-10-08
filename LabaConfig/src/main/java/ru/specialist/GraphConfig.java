package ru.specialist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import java.util.ArrayList;
@Configuration
@PropertySource("graph.properties")// Указывает файл,из которого будут извлекаться данные для иниализации свойств
public class GraphConfig {
	@Autowired
	private Environment e;
	@Bean
	@Scope("prototype")
	public Coords coords() {
		return new Coords();
	}
	@Bean("myCircle")
	@Scope("prototype")
	public Circle circle() {
		Circle circle1=new Circle("blue",coords());
		circle1.setRadius(e.getProperty("myCircle.radius",Integer.class,2));
		return circle1;
	}
	@Bean
	@Scope("prototype")
	public Point point() {
		Point p=new Point("grey",coords());
		return p;
	}
	@Bean
	@Scope("prototype")
	public Triangle triangle() {
			Triangle t=new Triangle("grey",coords(),coords(),coords());
			t.setX1(e.getProperty("Triangle.x1",Integer.class,5));
			t.setX1(e.getProperty("Triangle.y1",Integer.class,5));
			t.setX1(e.getProperty("Triangle.x2",Integer.class,4));
			t.setX1(e.getProperty("Triangle.y2",Integer.class,3));
			t.setX1(e.getProperty("Triangle.x3",Integer.class,6));
			t.setX1(e.getProperty("Triangle.y3",Integer.class,5));
			return t;	
	}	
	@Bean
	@Lazy
	public Scene scene() {
		Scene l=new Scene( new ArrayList<Shape>());
		l.getObjects().add(point());
		l.getObjects().add(circle());
		l.getObjects().add(triangle());
		l.getObjects().add(point());
		return l;
	}
		
	
}
