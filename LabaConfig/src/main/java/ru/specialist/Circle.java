package ru.specialist;

public class Circle extends Shape{
	private Coords center;
	private int radius;
	public int getX() {
		return center.getX();
	}
	public int getY() {
		return center.getY();
	}
	public void setX(int x) {
		center.setX(x);
	}
	
	public void setY(int y) {
		center.setY(y);
	}
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void draw() {
		System.out.printf("Circle(%d, %d),radius %d,color %s\n",getX(),getY(),getRadius(),getColor());
	}
	public Circle(String color,Coords center) {
		super(color);
		this.center=center;
	}
}
