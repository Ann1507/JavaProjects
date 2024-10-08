package ru.specialist;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WoodWindow implements Window{
	public void open() {
		System.out.println("Open wood window");
	}
}
