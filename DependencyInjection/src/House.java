
public class House {
	private Window window;
	private Door mainDoor;
	public Door getMainDoor() {
		return mainDoor;
	}
	public void setMainDoor(Door mainDoor) {
		this.mainDoor=mainDoor;
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
}
