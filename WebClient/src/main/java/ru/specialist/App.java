package ru.specialist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class App {

	public static final String COURSES_SERVICE_BASE_ADDRESS="http://localhost:8080/WebJava/api/course";
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		System.out.println("web service http client");
		
		HttpRequest request = HttpRequest
				.newBuilder(new URI(COURSES_SERVICE_BASE_ADDRESS))//Создание строителя(объект)-предназнач.для создания сложных объектов(у которых много параметров), который будет создавать httpRequest
				.header("Accept-Charset","utf-8")//задание параметра строителя (заголовок запроса)
				.GET()//задание параметра стррителя (команда протокола http -get)
				.build();//строитель создает объект httpRequest
		
		HttpResponse<InputStream> response = HttpClient
				.newBuilder()//Создание строителя предназн.для создания httpClient(это объект который будет деоать http запрос)
				.build()//создание объекта httpClient
				.send(request,BodyHandlers.ofInputStream()/*.ofString(Charset.forName("utf-8"))*/);//выполнение запроса(отправка запроса и получение ответа)
		InputStreamReader reader = new InputStreamReader(response.body());
		//BufferedReader reader2 = new BufferedReader(reader);
		String result = IOUtils.toString(reader);
		System.out.printf("Status code: %d\nResponse body:\n%s\n",
				response.statusCode(),result);
		
		

	}

}
