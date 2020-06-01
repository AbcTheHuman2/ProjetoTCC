package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO implements iGenericDAO {

	private Connection c;
	
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/db_fatec;"
				+ "namedPipe=true;instance=SQLEXPRESS;", "fatec_tcc", "123");
		System.out.println("Conexão OK!");
		return c;
	}

}
