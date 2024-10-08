package ru.specialist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Point extends Shape{
	private Coords coords;
	@Override
	public void draw() {
		System.out.printf("Point(%d, %d) %s\n",getX(),getY(),getColor());
	}
	public int getX() {
		return coords.getX();
	}
	public int getY() {
		return coords.getY();
	}
	//@Value("8")
	public void setX(int x) {
		coords.setX(x);
	}
	//@Value("5")
	public void setY(int y) {
		coords.setY(y);
	}
	
	public Coords getCoords() {
		return coords;
	}
	//@Value("#{coords}")
	@Autowired //автоматическое связывание(присваивание) значения подходящее по типу бина(компонента спринга)(или имени)
	public void setCoords(Coords coords) {
		this.coords = coords;
	}
	public Point(@Value("red") String color/*,@Value("#{coords}") Coords coords*/) {
		super(color);
		//this.coords=coords;
	}
	
}
