package ru.specialist;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Scene {
	private List<Shape> objects;
	
	@Autowired
	public Scene(List<Shape> objects) {
		this.objects=objects;
	}
	
	public void drawScene() {
		for(Shape shape:objects)
			shape.draw();
	}
}
