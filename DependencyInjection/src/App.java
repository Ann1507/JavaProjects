
public class App {

	public static void main(String[] args) {
		//new Builder().createHouse().ventilate();
		Builder b=new Builder();
		House h=b.createHouse();
		h.ventilate();
		h.installDoors();
	}

	
}
