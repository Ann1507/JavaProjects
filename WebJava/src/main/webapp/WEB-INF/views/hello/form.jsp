<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
	${user.hello}
</h1>
	<form method="POST" action="hello">
		<fieldset>
			<div>
		<select name="prefix"> 
		<option ${user.prefix.equals("Ms")?"selected":"" } value="Ms">Ms </option>
		<option ${user.prefix.equals("Mr")?"selected":"" } value="Mr">Mr </option>
		<option ${user.prefix.equals("Miss")?"selected":"" } value="Miss">Miss </option>

		</select>
				<label>Ваше имя: </label>
				<input  type="text" name="userName" value="${user.userName }" >
				
			</div>
			<div>
				<input type="submit" value="Привет!">
			</div>
		</fieldset>
		
	</form>
</body>
</html>

<!-- 
Нужно к этой форме выбор префикса мистер,мисис,мисс и далее имя.
1. Добавить к модели UserVM строковое свойство prefix(методы getPrefix и setPrefix)
2. Модифицировать метод getHello в модели UserVM т.о. чтобы перед именем подставлялся префикс.
3. На форме (form.jsp) добавить выпадающий список с тремя вариантами:Mr.,Miss,Missis.

<select name="prefix"> 
<option value="Miss">Miss</option>
<option value="Mr">Mr</option>
<option value="Missis">Missis</option>

</select>
4. Модифицировать метод showHello в hello контроллере чтобы происходило копирование префикса по аналогии с userName
 -->