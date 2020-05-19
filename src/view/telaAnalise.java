package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		frame_analise = new JFrame("Analisando Imagem...");
		frame_analise.setBounds(100, 100, 665, 418);
		frame_analise.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_analise.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Comparando com fotos do Banco de Dados...");
		lblNewLabel.setBounds(340, 65, 277, 14);
		frame_analise.getContentPane().add(lblNewLabel);
		frame_analise.setLocationRelativeTo(null);
	}
}
