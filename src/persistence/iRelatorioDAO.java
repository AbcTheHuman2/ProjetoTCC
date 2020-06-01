package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import model.Relatorio;

public interface iRelatorioDAO {
	
	public void insereRelatorio(Relatorio rel, Connection c) throws SQLException;
	public void atualizaRelatorio(Relatorio rel, Connection c) throws SQLException;
	public Relatorio preencheRelatorio(Connection c) throws SQLException;
}
