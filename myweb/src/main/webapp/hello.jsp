<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.time.LocalTime"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String hstr=request.getParameter("hours");
	int h=(hstr==null|| hstr.isEmpty())? LocalTime.now().getHour():Integer.parseInt(hstr);
	request.setCharacterEncoding("utf-8");
	String userName=request.getParameter("userName");
	//if(userName==null|| userName.isBlank())
	 	//userName="";
	userName=(userName==null|| userName.isBlank()) ? "" : userName.trim();//trim-метод котор.убирает пробелы в нач и конце строки
	String agestr=request.getParameter("age");
	int age=(agestr==null || agestr.isBlank()) ? -1 : Integer.parseInt(agestr);
	//String hello=(age>=18 || age<0) ? "Здравствуйте" : "Привет";
	
	%>
	<h1>
	<%
	String hello=null;
	if(h>=0 && h<6) 
		hello="Доброй ночи";
	if(h>=6 && h<12) 
		hello="Доброе утро";
	if(h>=12 && h<18) 
		hello="Добрый день";
	if(h>=18 && h<=23) 
		hello= "Добрый вечер";
	//out.print(String.format("%s, %s!",hello,userName));
	if(userName.isEmpty()) 
		out.print(hello +"!");
	else
	   out.print(String.format("%s, %s!",hello,userName));
	%>
	</h1>
	<!--
	JSP страница состоит из статич. и динамической части.Статическая часть -это обычный код
	html,динамич.часть это серверные теги(<% %>),содержащие код на java.
	При обращении к JSP странице внутри сервера Tomcat запскается спец.сервлет Jasper,кот.передается JSP страница
	Сервлет Jasper вытаскивает из серверных тегов код java компилирует его и выполняет.
	Результат выполнения(out.print)вставляется на страницу вместо серверных тегов,
	получившийся конечный html передается браузеру.	
	 -->
	<form method="POST" >
		<label>Ваше имя:</label><!-- Подсказка -->
		<input type="text" name="userName" value="<%=userName%>"><!--Текстовое поле для ввода имени -->
		<!-- Если внутри серверного тега только одна команда out.print то 
		вместо нее можно написать знак равно без ; в конце -->
		<label>Возраст: </label>
		<input type=number name=age value="<%=agestr%>">
		<input type="submit" value="Привет"><!--Кнопка для отправки данных на сервер -->
		<label>Время суток </label>
		<input type=number name="hours" value="<%=h%>">
		<input type="submit" value="Узнать время">
		
		
	</form>
</body>
</html>
<!-- В зависимости от времени суток(часов) выводить приветствие: 
0..6 -Доброй ночи,имя!
6..12-Доброе утро,имя!
12..18-Добрый день,имя!
18..23-Добрый вечер,имя!

-->