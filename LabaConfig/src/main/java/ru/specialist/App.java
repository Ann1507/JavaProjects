package ru.specialist;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class App {
	public static void printColor(String color) {
		System.out.println(color);
	}
	public static void main(String[] args) {
		//Создаем контекст-он дает доступ к контейнеру спринга который отвечает за создание бинов и связывание их друг с другом в соответствии с конфигурацией
		ApplicationContext context=new AnnotationConfigApplicationContext(GraphConfig.class);
		/*Point p=context.getBean(Point.class);
		Circle c=context.getBean(Circle.class);
		Triangle t=context.getBean(Triangle.class);
		p.draw();
		c.draw();
		t.draw();*/
		Scene c1=context.getBean(Scene.class);
		c1.drawScene();
		//System.out.printf("Circle radius: %d\n",c.getRadius());
	}
}
//Тип данных int-значение конкретные числа
//Тип данных класс-значение(экземпляры)конкретные объекты
//Добавить класс треугольник,наследник Shape. Определить в нем три вершины А Б и С, в виде трех полей типа coords
// Реализовать три пары get set. Реализовать конструктор с нужными параметрами,метод draw,который напечает три пары координат вершин треугольника и его цвет.
//в программе получить из context треугольник методом getbean и вызвать у него метод draw().
