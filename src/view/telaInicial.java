package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.Relatorio;

public class telaInicial {

	private JFrame frame;
	
	Relatorio rel = new Relatorio();

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
		btnSelecionarArquivo.setBounds(51, 106, 180, 23);
		frame.getContentPane().add(btnSelecionarArquivo);
		
		String vlrEstado;
		boolean estado = rel.isEstado();
		
		if (estado) {
			vlrEstado = "<html><font color='green'>Nome do arquivo</font></html>";
		} else {
			vlrEstado = "<html><font color='red'>Arquivo não encontrado</font></html>";
		}
		
		JLabel lblEstado = new JLabel(vlrEstado);
		lblEstado.setBounds(241, 110, 308, 14);
		frame.getContentPane().add(lblEstado);
		
		JButton btnGerarRelatrio = new JButton("Gerar Relatório");
		btnGerarRelatrio.setBounds(157, 201, 132, 23);
		frame.getContentPane().add(btnGerarRelatrio);
		
		btnSelecionarArquivo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		btnGerarRelatrio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
	}
}
