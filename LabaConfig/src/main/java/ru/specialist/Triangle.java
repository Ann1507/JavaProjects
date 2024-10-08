package ru.specialist;

public class Triangle extends Shape {
	private Coords A,B,C;
	
	public int getX1() {
		return A.getX();
	}
	
	public int getY1() {
		return A.getY();
	}
	
	public void setX1(int x) {
		A.setX(x);
	}
	
	public void setY1(int y) {
		A.setY(y);
	}
	public int getX2() {
		return B.getX();
	}
	
	public int getY2() {
		return B.getY();
	}
	
	public void setX2(int x) {
		B.setX(x);
	}
	public void setY2(int y) {
		B.setY(y);
	}
	
	public int getX3() {
		return C.getX();
	}
	
	public int getY3() {
		return C.getY();
	}
	
	public void setX3(int x) {
		C.setX(x);
	}
	
	public void setY3(int y) {
		C.setY(y);
	}
	public Triangle(String color, Coords A,Coords B, Coords C) {
		super(color);
		this.A=A;
		this.B=B;
		this.C=C;
	}
	@Override
	public void draw() {
		System.out.printf("Triangle: A(%d, %d),B(%d, %d),C(%d, %d),color: %s\n",getX1(),getY1(),getX2(),getY2(),getX3(),getY3(),getColor());
	}
}
