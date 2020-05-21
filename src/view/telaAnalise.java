package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ComparaImagensController;
import model.Relatorio;

public class telaAnalise {

	public JFrame frame_analise;

	Relatorio rel = new Relatorio();
	
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
		Relatorio r = new Relatorio();
		
		frame_analise = new JFrame("Analisando Imagem...");
		frame_analise.setBounds(100, 100, 665, 418);
		frame_analise.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_analise.getContentPane().setLayout(null);
		
		JLabel lblFoto = new JLabel("");
		
		lblFoto.setIcon(rel.getImagem());
		lblFoto.setBounds(34, 32, 261, 292);
		frame_analise.getContentPane().add(lblFoto);
		frame_analise.setLocationRelativeTo(null);
		
		Relatorio[] relatorio = ci.gerarRelatorio(r);
	}
}
