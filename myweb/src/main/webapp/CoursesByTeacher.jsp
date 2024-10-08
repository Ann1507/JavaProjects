<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function teachersSelected(){
	document.getElementById("myform").submit();//найти элемент на странице по его id,метод submit формы эквивалентен нажатию на кнопку submit
}
</script>
</head>
<body>
<h1>учебные курсы</h1>
<%
String teacherIDstr=request.getParameter("teacher");
int teacherId=(teacherIDstr==null|| teacherIDstr.isEmpty())? 0:Integer.parseInt(teacherIDstr);
final String DRIVER_CLASS_NAME="com.mysql.jdbc.Driver";
final String CONNECTION_STRING="jdbc:mysql://localhost:3306/web?user=root&password=demo";
Class.forName(DRIVER_CLASS_NAME);//принудительная загрузка класса драйвера,необх.для автоматической регистрации драйвера в драйверменеджере 
try (Connection conn=DriverManager.getConnection(CONNECTION_STRING)){%>
<form method="POST" id="myform" > <!-- form -это прямоугольная область на странице,внутри которой размещаются элементы управлния,а элементы управления это визуальные элементы с которыми может взаимод.пользлователь например теги select или input -->
		<select name="teacher" onchange="teachersSelected()">
		<option value=0>...</option>
		<%
		String sql="select id,name from teachers order by name";
        Statement cmd=conn.createStatement();
        try(ResultSet result=cmd.executeQuery(sql)){ 
            while(result.next()){
                String name=result.getString("name");
                int id=result.getInt("id");
				if(id==teacherId)
                	out.print(String.format("<option selected value=%d>%s</option>",id,name));
				else 
                	out.print(String.format("<option value=%d>%s</option>",id,name));

            }
        }
		%>
				
		</select>
		<input type="submit" value="Найти"><!--Кнопка для отправки данных на сервер -->
				
	</form>
	<table border=1>
	<tr>
		<th>Название</th>
		<th>Длительность</th>
		<th>Описание</th>
	</tr>
	<%
	    CallableStatement sp=conn.prepareCall("call coursesByTeacherId(?)");//создаем коменду для выполнения селекта
        sp.setInt(1, teacherId);
        if(sp.execute()){//вызов хранимой процедуры.Метод execute возращ.истину если результатом является ResultSet
        	try(ResultSet result=sp.getResultSet()){ //метод getResultSet возвраш.ResultSet полученный предыдущим вызовом execute
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
<!-- 1.Заполнить содержимое тега select данными из БД ддля этого выполнить запрос из таблицы teachers получив список преподавателей в виде id  и имя преподавателя
На основе этого списка внутри тега селект сформировать список тегов option записав id  взначение атрибута value тега option а имя м/у закрывающ открывающим тегом option.
2.Для выбранного преподавателя (teacherId) добавить атрибут selected тег option.
3.Модифицировать хранимую процедуру coursesByTeacherId так что если teacherId=0 то получать список всех курсов.-->