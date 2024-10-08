<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	form fieldset {
		width : 40%;
	} 
	form fieldset label {
		display : block;
		/*width : 50%;
		float :left;*/
	}
	form fieldset input, form fieldset textarea {
		width : 100%;
	}
	
	.error {
		color : red;
	}
</style>
</head>
<body>
	<h1>Course</h1>
	<c:if test="${not empty error}">
		<h2 class="error">${error}</h2>
	</c:if>
	<form method = "POST" >
		<fieldset>
			<div>
				<label>Название: </label>
				<input type="text" name="title" value="${course.title}">
				
			</div>
			<div>
				<label>Длительность: </label>
				<input type="number" name="length" value="${course.length}">
				
			</div>
			<div>
				<label>Описание: </label>
				<textarea name="description" rows="7">${course.description}</textarea>
				
			</div>
			<input type="submit" value="сохранить">
		</fieldset>
	</form>
</body>
</html>