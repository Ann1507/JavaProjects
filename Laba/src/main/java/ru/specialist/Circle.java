package ru.specialist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Circle extends Shape{
	private Coords center;
	private int radius;
	public int getX() {
		return center.getX();
	}
	public int getY() {
		return center.getY();
	}
	@Value("${myCircle.x}")// Подставить значение из Properties файла по указанному имени
	public void setX(int x) {
		center.setX(x);
	}
	@Value("${myCircle.y}")
	public void setY(int y) {
		center.setY(y);
	}
	public int getRadius() {
		return radius;
	}
	@Value("${myCircle.radius}")
	public void setRadius(int radius) {
		this.radius = radius;
	}
	@Override
	public void draw() {
		System.out.printf("Circle(%d, %d),radius %d,color %s\n",getX(),getY(),getRadius(),getColor());
	}
	public Circle(@Value("${myCircle.color}")String color,@Value("#{coords}")Coords center) {
		super(color);
		this.center=center;
	}
}
