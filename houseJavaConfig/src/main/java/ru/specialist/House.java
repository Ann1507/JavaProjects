package ru.specialist;
//POJO Class- Plain old java object-старый добрый джава объект(любой джава объект не привязанн.к спригу)
public class House {
	private Window window;
	private int height;
	private Door mainDoor;
	public Door getMainDoor() {
		return mainDoor;
	}
	public void setMainDoor(Door mainDoor) {
		this.mainDoor=mainDoor;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	public Window getWindow() {
		return window;	
	}
	public void setWindow(Window window) {
		this.window=window;
	}
	public void ventilate() {
		getWindow().open();
	}
	public void installDoors() {
		getMainDoor().install();
	}
	public House(Window window) {
		System.out.println("House constructor");
		this.window=window;
	}
	
	
}
