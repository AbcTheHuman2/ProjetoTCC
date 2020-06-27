package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import model.Relatorio;

public interface iRelatorioDAO {
	
	public void insereRelatorio(Relatorio rel, Connection c) throws SQLException;
	public Relatorio atualizaRelatorio(Relatorio r, Connection c) throws SQLException;
	public Relatorio preencheRelatorio(Connection c) throws SQLException;
}
