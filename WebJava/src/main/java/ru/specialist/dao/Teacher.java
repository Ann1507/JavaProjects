package ru.specialist.dao;
import jakarta.persistence.Column;
//java enterprise edition-пакет с аннотациями для разметки класса сущности
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity//обозн класс сущности(объекты кот.будут загружаться из БД и туда сохраняться)
@Table(name="teachers")
public class Teacher {
	private int id;
	private String name;
	private String addr;
	private String phone;
	@Column(name="id")//связывает свойства сущности с колонкой таблицы
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="addr")
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return String.format("%-3d %-25s %-35s %-9s", 
				getId(), getName(), getAddr(),getPhone());
	}
}
