package ru.specialist.reportjdbc;
import java.sql.*;

public class App {
	public static final String DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
	public static final String CONNECTION_STRING=
			"jdbc:mysql://localhost:3306/northwind?user=root&password=demo";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS);
		Connection conn=DriverManager.getConnection(CONNECTION_STRING);
		
		String sql="select categoryName,productName,unitPrice "
				+ "from category inner join product on category.categoryId=product.categoryId "
				+ "where unitPrice in (select max(unitPrice) from product where categoryId=category.categoryId ) "
				+ "order by categoryName,productName";
		
		Statement cmd=conn.createStatement();
		ResultSet result=cmd.executeQuery(sql);
		while(result.next()) {
			String categoryName=result.getString("categoryName");
			String productName=result.getString("productName");
			float unitPrice=result.getFloat("unitPrice");
			System.out.printf("%-15s %-15s %10.2f\n", categoryName,productName,unitPrice);
			
		}
		result.close();
		conn.close();

	}

}
