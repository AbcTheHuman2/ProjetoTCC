package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import model.Relatorio;

public class RelatorioDAO implements iRelatorioDAO {
	
	private Connection c;
	
	public RelatorioDAO(Connection c) throws ClassNotFoundException, SQLException {
		iGenericDAO gDAO = new GenericDAO();
		c = gDAO.getConnection();
	}

	@Override
	public void insereRelatorio(Relatorio rel) throws SQLException {
		
		String sql = " INSERT INTO RELATORIOS (id, imagem, dta) VALUES (?, ?, ?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		
		//formatando data atual para inserir no banco
		try {  
			LocalDateTime ldt = LocalDateTime.now();
			String data = ldt.toString();
			Date data_formatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data);
			rel.setData(data_formatada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ResultSet rs = ps.getResultSet();
		int id = rs.getInt(0);
		rel.setId(id);
		
		ps.setInt(0, rel.getId());
		ps.setBytes(1, rel.getFoto());
		ps.setDate(2, java.sql.Date.valueOf(rel.getData().toString()));
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizaRelatorio(Relatorio rel) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
