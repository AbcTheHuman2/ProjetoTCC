package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private Connection conn = null;
	private String query = "";
	private ResultSet rs;
	
	//Nome do banco, login e senha ainda não definidos
	private final String url=("jdbc:sqlserver://localhost:1433;databaseName=banco");
	private final String userName = "sa";
	private final String pwd = "123";
	
	
	public DBConnection(Connection conn, String query, ResultSet rs) {
		try {
			conn = DriverManager.getConnection(url, userName, pwd);
			
			//Nome da tabela ainda não definido
			query = " SELECT * FROM tabela";
			PreparedStatement ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Password");
				
				System.out.println("Username: " + username + "\n Password: " + password);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
}