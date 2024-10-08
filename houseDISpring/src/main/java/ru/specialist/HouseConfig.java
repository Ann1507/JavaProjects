package ru.specialist;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("ru.specialist")//Указывает в каком пакете или в каких пакетах искать классы на сонове которых будут создаваться компоненты спринга
@PropertySource("house.properties")
public class HouseConfig {

}
