package ru.specialist;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {

	public static final String COURSES_SERVICE_BASE_ADDRESS="http://localhost:8080/WebJava/api/course";
	public static final int CONNECTIONS = 1000;
	
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		System.out.println("web service http client POST");
		//создание задачи экземпляры которой будут параллельно выполняться
		Runnable task = () -> {
			
			try {
				Course c=new Course();
			
				c.setTitle("Java 1");
				c.setLength(40);
				c.setDescription("Java intro");
				Gson g=new GsonBuilder().setPrettyPrinting().create();
				String json=g.toJson(c);
				System.out.println(json);
				HttpRequest request = HttpRequest
						.newBuilder(new URI(COURSES_SERVICE_BASE_ADDRESS))//Создание строителя(объект)-предназнач.для создания сложных объектов(у которых много параметров), который будет создавать httpRequest
						.header("Content-Type","application/json")//задание параметра строителя (заголовок запроса)
						.POST(HttpRequest.BodyPublishers.ofString(json, Charset.forName("utf-8")))//задание параметра стррителя (команда протокола http -get)
						.build();//строитель создает объект httpRequest
				
				HttpResponse<InputStream> response = HttpClient
						.newBuilder()//Создание строителя предназн.для создания httpClient(это объект который будет деоать http запрос)
						.build()//создание объекта httpClient
						.send(request,BodyHandlers.ofInputStream());//выполнение запроса(отправка запроса и получение ответа)
				InputStreamReader reader = new InputStreamReader(response.body());
				//BufferedReader reader2 = new BufferedReader(reader);
				//String result = IOUtils.toString(reader);
				System.out.printf("Status code: %d\n \n",
						response.statusCode());
				Course r=g.fromJson(reader, Course.class);
				System.out.println(r);
			} catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		};
		//Создание пула задач фиксированного размера
		//Пул задач это контейнер для множества задач выполняющихсся параллельно ассинхронно
		//таким образом мы имимтируем множество клиентов выполняющих параллельные запросы
		ExecutorService p = Executors.newFixedThreadPool(CONNECTIONS);
		for (int i = 0; i <  CONNECTIONS;i++)
			p.submit(task);//отправляем задачу в пул, где она запускается ассинхронно(не ждем выполнения др.задач,параллельно др.задачам)
		
		p.shutdown();//завершение работы пула(после того как все задачи завершается)
	}

}
