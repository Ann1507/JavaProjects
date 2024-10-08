package ru.specialist;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MetalDoor implements Door {
	public void install() {
		System.out.println("Install metal door");
	}
	public MetalDoor() {
		System.out.println("MetalDoor constructor");
	}
}
