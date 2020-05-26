package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Relatorio;

public class RelatorioDAO implements iRelatorioDAO {
	
	private Connection c;
	
	public RelatorioDAO(Connection c) throws ClassNotFoundException, SQLException {
		iGenericDAO gDAO = new GenericDAO();
		c = gDAO.getConnection();
	}

	@Override
	public void insereRelatorio(Relatorio rel) throws SQLException {
		
		String sql = " INSERT INTO RELATORIOS (estado, imagem) VALUES (?, ?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		
		String estado = "";
		
		if (rel.isEstado()) {
			estado = "S";
		} else {
			estado = "N";
		}
		
		ps.setString(0, estado);
		ps.setBytes(1, rel.getFoto());
		ps.execute();
		ps.close();
	}
}
