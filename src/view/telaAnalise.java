package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

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
		frame_analise.setBounds(100, 100, 450, 300);
		frame_analise.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_analise.getContentPane().setLayout(null);
		frame_analise.setLocationRelativeTo(null);
	}

}
