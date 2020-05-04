package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

public class telaInicial {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaInicial window = new telaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSelecionarArquivo = new JButton("Selecionar Arquivo");
		btnSelecionarArquivo.setBounds(103, 136, 123, 23);
		frame.getContentPane().add(btnSelecionarArquivo);
		
		JLabel lblEstado = new JLabel("Arquivo n\u00E3o encontrado");
		lblEstado.setBounds(233, 140, 149, 14);
		frame.getContentPane().add(lblEstado);
	}
}
