package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Relatorio;

public class RelatorioDAO implements iRelatorioDAO {

	@Override
	public void insereRelatorio(Relatorio rel, Connection c) throws SQLException {
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		Calendar cal = Calendar.getInstance();
		
		String sql = " INSERT INTO RELATORIOS (imagem, dta) VALUES (?, ?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		rel.setData(df.format(cal.getTime()));
		
		ps.setBytes(1, rel.getFoto());
		ps.setString(2, rel.getData());
		ps.execute();
		ps.close();
		
		System.out.println("Um novo relatório foi inserido!");
	}

	@Override
	public Relatorio atualizaRelatorio(Relatorio r, Connection c) throws SQLException {
		
		String sql = " UPDATE RELATORIOS SET eh_cafe = ?, n_frutos = ?, verdes = ? "
				+ "WHERE id = ? ";
		
		PreparedStatement ps = c.prepareStatement(sql);
		boolean b = false;
		int x = 0, y = 0;
		
		r.setEh_cafe(b);
		r.setN_frutos(x);
		r.setFrutos_vermelhos(y);
		
		String str_b = "";
		
		if (b) {
			str_b = "S";
		} else {
			str_b = "N";
		}
		
		ps.setString(1, str_b);
		ps.setInt(2, r.getN_frutos());
		ps.setInt(3, r.getN_frutos());
		ps.setInt(4, r.getId());
		ps.execute();
		ps.close();
		
		System.out.println("Relatório atualizado!");
		return r;
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
				r.setData(rs.getString("dta"));
			}
		}
		
		System.out.println("Relatório preenchido!");
		return r;
	}
}
