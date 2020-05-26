package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO implements iGenericDAO {

	private Connection c;
	
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		//Nome do banco, login e senha ainda não definidos
		c = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;"
				+ "DatabaseName=master;namedPipes=true",
				"fatec_tcc", "123");
		return c;
	}

}
