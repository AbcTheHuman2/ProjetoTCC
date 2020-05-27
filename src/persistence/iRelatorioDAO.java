package persistence;

import java.sql.SQLException;
import model.Relatorio;

public interface iRelatorioDAO {
	
	public void insereRelatorio(Relatorio rel) throws SQLException;
	public void atualizaRelatorio(Relatorio rel) throws SQLException;
}
