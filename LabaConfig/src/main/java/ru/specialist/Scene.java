package ru.specialist;
import java.util.List;
public class Scene {
	private List<Shape> objects;
	
	
	public Scene(List<Shape> objects) {
		this.objects=objects;
	}
	
	public void drawScene() {
		for(Shape shape:objects)
			shape.draw();
	}
	public List<Shape> getObjects(){
		return objects;
	}
}
