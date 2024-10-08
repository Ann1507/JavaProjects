<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,java.util.Scanner"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>учебные курсы</h1>
<%
request.setCharacterEncoding("UTF-8");//чтобы не было просблем с русскими буквами
String search=request.getParameter("search");
search=(search==null)? "": search.trim();
%>
<form method="POST" >
		<label>Найти курсы:</label><!-- Подсказка -->
		<input type="text" name="search" value="<%=search%>"><!--Текстовое поле для ввода имени -->
		<!-- Если внутри серверного тега только одна команда out.print то 
		вместо нее можно написать знак равно без ; в конце -->
		<input type="submit" value="Найти"><!--Кнопка для отправки данных на сервер -->
		
	</form>
<table border=1>
	<tr>
		<th>Название</th>
		<th>Длительность</th>
		<th>Описание</th>
	</tr>
	<%
	final String DRIVER_CLASS_NAME="com.mysql.jdbc.Driver";//это имя класса драйвера которое нужно принудительно загрузить,чтобы он сам себя зарегистр.в Драйверменеджере(класс из пакета джава SQLс пом екотор откроем соединение с БД)
    final String CONNECTION_STRING="jdbc:mysql://localhost:3306/web?user=root&password=demo";
    Class.forName(DRIVER_CLASS_NAME);//принудительная загрузка класса драйвера,необх.для автоматической регистрации драйвера в драйверменеджере 
    try (Connection conn=DriverManager.getConnection(CONNECTION_STRING)){ //Открываем соединение с БД
        CallableStatement cmd=conn.prepareCall("call findCourses(?)");//создаем коменду для выполнения селекта
        cmd.setString(1, "%"+search+"%");
        if(cmd.execute()){//вызов хранимой процедуры.Метод execute возращ.истину если результатом является ResultSet
        	try(ResultSet result=cmd.getResultSet()){ //метод getResultSet возвраш.ResultSet полученный предыдущим вызовом execute
                while(result.next()){
                    String title=result.getString("title");
                    int length=result.getInt("length");
                    String description=result.getString("description");
                    out.print("<tr>");
                    out.print(String.format("<td>%s</td>",title));
                    out.print(String.format("<td>%d</td>",length));
                    out.print(String.format("<td>%s</td>",description));
                    out.print("</tr>");
                }        	  
            }//неявно выполнился result.close()      
        }
        //String sql="select title,length,description from courses order by title";
        //Statement cmd=conn.createStatement();//создаем коменду для выполнения селекта
        
    } //Неявно выполнится conn.close()
	%>
	<!--<tr>
		<td>Java1</td>
		<td>40</td>
		<td>Введение в java</td>
	</tr>
	<tr>
		<td>Java2</td>
		<td>40</td>
		<td>Клиент-серверное приложение на java</td>
	</tr>
	-->
</table>
</body>
</html>
<!-- Добавить наверх страницы форму с элементом управления типа текст и кнопкой Submit
	(форму можно скопировать со страницы hello.jsp.)Текстовому полю указать имя Search
	на кнопке сделатб надпись Найти.
	 Модтфтцировать код java так чтобы выполнялся параметризированный запрос к БД и извлекались только те курсы,в название которых
	 естьслово введеное пользователем в текстовое поле(по аналогии с консольным приложением dbselect) 
	 Как созадть функцию и включить ее в запрос,т.е чтлбы сначала она работала,потом после отработки где то выводить результат причем фуекция агрегирует данные неск систем..?
	 разобрать строку из длселект calleble-->