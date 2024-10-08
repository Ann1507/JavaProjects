
public class Builder {//здесь происходит связывание конкретного дома с конкретным окном и дверью
	//мы делегировали связывание в отдельный класс Builder это и есть dependencyInjection(внедрение зависимостей)
	public House createHouse() {
		House h=new House();
		Window w=new PlasticWindow();
		Door d=new MetalDoor();
		h.setWindow(w);
		h.setMainDoor(d);
		return h;
	}
}
//добавить к дому зависимость дверь,причем дверь может быть как деревянная так и металлическая.Для 
//дверей сделать метод install()вывести сообщение установлена дверь деревянная или металлическая.