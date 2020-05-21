package persistence;

import java.sql.SQLException;
import model.Relatorio;

public interface iRelatorioDAO {
	
	public void insereRelatorio(Relatorio rel) throws SQLException;
}
