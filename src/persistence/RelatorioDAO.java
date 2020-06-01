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

	@Override
	public void insereRelatorio(Relatorio rel, Connection c) throws SQLException {
		
		String sql = " INSERT INTO RELATORIOS (imagem, dta) VALUES (?, ?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		
		//formatando data atual para inserir no banco
		try {  
			LocalDateTime ldt = LocalDateTime.now();
			String data = ldt.getDayOfMonth() + "/" + ldt.getMonthValue() + "/" +
			ldt.getYear() + " - " + ldt.getHour() + ":" + ldt.getMinute() + ":" +
			ldt.getSecond();
			Date data_formatada = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(data);
			rel.setData(data_formatada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		java.sql.Date data_sql = new java.sql.Date(rel.getData().getTime());
		
		ps.setBytes(1, rel.getFoto());
		ps.setDate(2, data_sql);
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizaRelatorio(Relatorio rel, Connection c) throws SQLException {
		
		String sql = " UPDATE RELATORIOS SET eh_cafe = ? WHERE id = ? ";
	}

	@Override
	public Relatorio preencheRelatorio(Connection c) throws SQLException {
		
		Relatorio r = new Relatorio();
		
		String sql = " SELECT * FROM RELATORIOS ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			if (rs.isLast()) {
				r.setId(rs.getInt("id"));
				r.setFoto(rs.getBytes("imagem"));
				r.setData(rs.getDate("dta"));
			}
		}
		
		return r;
	}
}
