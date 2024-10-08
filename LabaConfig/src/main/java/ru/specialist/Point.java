package ru.specialist;

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
	
	public void setX(int x) {
		coords.setX(x);
	}
	
	public void setY(int y) {
		coords.setY(y);
	}
	
	public Coords getCoords() {
		return coords;
	}
	
	public void setCoords(Coords coords) {
		this.coords = coords;
	}
	public Point(String color, Coords coords) {
		super(color);
		this.coords=coords;
	}
	
}
