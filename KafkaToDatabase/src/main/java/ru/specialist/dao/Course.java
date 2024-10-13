//Класс Course который повторяет содержание(поля) таблицы из БД courses
//Такой класс назывется классом сущности(Entity Class)
package ru.specialist.dao;
import jakarta.persistence.*;//java enterprise edition-пакет с аннотациями для разметки класса сущности
@Entity//обозн класс сущности(объекты кот.будут загружаться из БД и туда сохраняться)
@Table(name="courses")//указывает в какую таблицу будут сохраняться объекты этого класса сущности
public class Course {
	private int id;
	private String title;
	private int length;
	private String description;
	@Column(name="id")//связывает свойства сущности с колонкой таблицы
	@Id//обозначает первичный ключ- идентификатор строки
	@GeneratedValue(strategy=GenerationType.IDENTITY)//указывает что значение этой колонки генерируется БД как автоинкремент
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="length")
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		
		return String.format("%-3d %-50s %4d", 
				getId(), getTitle(), getLength());
	}

}
