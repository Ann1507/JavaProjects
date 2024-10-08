package ru.anna;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class helloServlet
 */
public class helloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public helloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		String name=request.getParameter("userName");
		PrintWriter out=response.getWriter();//объект для формирования текста html страниц
		if(name==null|| name.isBlank())//учитывает пробелы
			//out.println("<h1>Привет!</h1>");
			response.sendRedirect("hello.html");
		else {
			String agestr=request.getParameter("age");
			if(agestr==null || agestr.isBlank())
				out.printf("<h1>Здравствуйте, %s!</h1>",name);
			else {
				int age=Integer.parseInt(agestr);
				if(age<18)
					out.printf("<h1>Привет, %s!</h1>",name);
				else
					out.printf("<h1>Здравствуйте, %s!</h1>",name);				
			}
			
		}
	}
		

}
// На форму добавить элемент управления для ввода возраста <input type=number name=age>
// В сервлете получит значение возраста если возраст меньше 18,то вывести Привет,имя,иначе
// вывести "Здравствуйте,имя"