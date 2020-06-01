package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ComparaImagensController;
import model.Relatorio;
import persistence.GenericDAO;
import persistence.RelatorioDAO;
import persistence.iGenericDAO;

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
		
		ComparaImagensController ci = new ComparaImagensController();
		iGenericDAO gDAO = new GenericDAO();
		Connection c;
		Relatorio r = new Relatorio();
		try {
			c = gDAO.getConnection();
			RelatorioDAO rDAO = new RelatorioDAO();
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
		frame_analise.setLocationRelativeTo(null);
		
		//Relatorio[] relatorio = ci.gerarRelatorio(r);
	}
	
    public void setVisible(boolean b) {
    	telaAnalise window = new telaAnalise();
		window.frame_analise.setVisible(b);
    }

}
