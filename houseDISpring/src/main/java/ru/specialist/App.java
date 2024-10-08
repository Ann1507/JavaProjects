package ru.specialist;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		System.out.println("Before context creation");
		//Создаем контекст-он даеит доступ к контейнеру спринга который отвечает за создание бинов и связывание их друг с другом в соответствии с конфигурацией
		ApplicationContext context=new AnnotationConfigApplicationContext(HouseConfig.class);
		System.out.println("After context creation");
		//Методом getBean получаем из контейнера bean (объект) типа House,который бцдет контейнером автоматически создан и связан с другими бинами
		House h=context.getBean(House.class);
		h.ventilate();
		h.installDoors();
		House h1=context.getBean(House.class);
		System.out.println(h==h1);
		System.out.println(h.hashCode());
		System.out.println(h1.hashCode());
	}
	
}
/* Labs
 * создать класс Coords
 * 	с двумя полями интовые x,y
 * создать абстрактн класс abstract Shape
 * 	в нем поле типа String color
 *   метод abstract draw()
 *  
 * сделать класс Point extends Shape
 * 	в нем поле типа Coords coords
 *  реализоваь метод draw();
 *  реализовать setX(), setY() для координат
 * 
 * сделать класс окружность Circle extends Shape
 *   поля Coords center, int radius
 *   метод для них draw() вывести сообщение что такие коодинаты
 *   
 *   Beans: Coords, Point, Circle
 *   С помощью аннотаций сделать три бина и в программе создать ApplicationContext,запросит у него бин точки и окружности и вызвать у них метод draw()
 *   бины Point Circle должны быть  связаны с бином coords  использовать аннотацию Value
 */
// Добавить в проект файл house.properties в кот.описать свойство house.height=2
// добавить в класс House свойство height: приватное поле height типа int и getHeight и setHeight
// добавить класс HouseConfig на котор навесить две аннотации:ComponentScan ,PropertySource с нужными параметрами
// С помощью аннотациия Value задать в классе House значение свойства height из файла с расширением properties house.properties 
