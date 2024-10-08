package ru.specialist;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class WoodDoor implements Door {
	public void install() {
		System.out.println("Install wood door");
	}
	public WoodDoor() {
		System.out.println("WoodDoor constructor");
				
	}
}