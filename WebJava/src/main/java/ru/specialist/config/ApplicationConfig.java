package ru.specialist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages= {"ru.specialist.controllers"})
// Корневая конфигурация для всех диспетчер сервлетов должна содержать описание
// всех общ.бинов(общ.для всех экземпляров диспетчер сервлетов)
// например, бины для работы с БД (если все диспетчер сервлеты будут работать с БД)
//также эта конфигурация бинов может содержать конфигурацию бинов связанных с безопасностью(SpringSecuirity)-аутентификация,авторизация-разграничение доступа
public class ApplicationConfig {

}
