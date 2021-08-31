package model.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBC {

	public static Connection connect() {
		Connection conn=null;
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3308/new_schema";
		try{
			Class.forName(jdbc_driver);
			conn=DriverManager.getConnection(jdbc_url,"root","1234");
		}
		catch(Exception e){
			System.out.println("connect()에서 출력");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void disconnect(PreparedStatement pstmt,Connection conn) {
		try{
			pstmt.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("disconnect()에서 출력");
			e.printStackTrace();
		}
	}
	
}