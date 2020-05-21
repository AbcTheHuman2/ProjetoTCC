package persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface iGenericDAO {

	public Connection getConnection() throws ClassNotFoundException, SQLException;
	
}
