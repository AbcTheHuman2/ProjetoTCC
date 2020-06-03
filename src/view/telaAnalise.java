package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ComparaImagensController;
import model.Relatorio;
import persistence.GenericDAO;
import persistence.RelatorioDAO;
import persistence.iGenericDAO;
import javax.swing.JButton;

public class telaAnalise {

	public JFrame frame_analise;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaAnalise window = new telaAnalise();
					window.frame_analise.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public telaAnalise() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		iGenericDAO gDAO = new GenericDAO();
		Connection c;
		Relatorio r = new Relatorio();
		RelatorioDAO rDAO = new RelatorioDAO();
		ComparaImagensController cic = new ComparaImagensController();
		
		try {
			c = gDAO.getConnection();
			r = rDAO.preencheRelatorio(c);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		frame_analise = new JFrame("Analisando Imagem...");
		frame_analise.setBounds(100, 100, 665, 418);
		frame_analise.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_analise.getContentPane().setLayout(null);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(34, 32, 261, 292);
		frame_analise.getContentPane().add(lblFoto);
		Image img = new ImageIcon(r.getFoto()).getImage();
		Image imgIcon = img.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT);
		
		lblFoto.setIcon(new ImageIcon(imgIcon));
		
		JLabel lblMsg = new JLabel("Comparando com fotos do banco de dados...");
		lblMsg.setBounds(337, 40, 278, 14);
		frame_analise.getContentPane().add(lblMsg);
		
		JLabel lblEstadoFoto = new JLabel("Estado Foto");
		lblEstadoFoto.setBounds(337, 65, 104, 14);
		frame_analise.getContentPane().add(lblEstadoFoto);
		
		JButton btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.setBounds(452, 61, 146, 23);
		frame_analise.getContentPane().add(btnMenuPrincipal);
		
		JLabel lblFrutosEncontrados = new JLabel("Frutos encontrados:");
		lblFrutosEncontrados.setBounds(337, 106, 171, 14);
		frame_analise.getContentPane().add(lblFrutosEncontrados);
		lblFrutosEncontrados.setVisible(false);
		
		JLabel lblFrutosVerdes = new JLabel("Frutos verdes:");
		lblFrutosVerdes.setBounds(337, 131, 135, 14);
		frame_analise.getContentPane().add(lblFrutosVerdes);
		lblFrutosVerdes.setVisible(false);
		
		JLabel lblFrutosMaduros = new JLabel("Frutos maduros:");
		lblFrutosMaduros.setBounds(337, 156, 125, 14);
		frame_analise.getContentPane().add(lblFrutosMaduros);
		frame_analise.setLocationRelativeTo(null);
		lblFrutosMaduros.setVisible(false);
		btnMenuPrincipal.setVisible(false);
		
		try {
			cic.validarRelatorio(r);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			c = gDAO.getConnection();
			rDAO.atualizaRelatorio(r, c);
			
			if (r.isEh_cafe()) {
				lblEstadoFoto.setText("<html><font color='green'>Foto válida!</font></html>");
				lblFrutosEncontrados.setVisible(true);
				lblFrutosVerdes.setVisible(true);
				lblFrutosMaduros.setVisible(true);
			} else {
				lblEstadoFoto.setText("<html><font color='red'>Foto inválida!</font></html>");
				btnMenuPrincipal.setVisible(true);
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		btnMenuPrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame_analise.setVisible(false);
				new telaInicial().setVisible(true);
			}
		});
	}
	
    public void setVisible(boolean b) {
    	telaAnalise window = new telaAnalise();
		window.frame_analise.setVisible(b);
    }
}
