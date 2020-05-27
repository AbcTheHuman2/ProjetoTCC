package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO implements iGenericDAO {

	private Connection c;
	
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		//login e senha do sql server
		String user = "fatec_tcc";
		String pwd = "123";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;"
				+ "DatabaseName=db_fatec;namedPipes=true", user, pwd);
		System.out.println("Conexão OK!");
		return c;
	}

}
