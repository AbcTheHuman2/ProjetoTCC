package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import controller.RelatorioController;
import model.Relatorio;

public class telaInicial {

	public JFrame frame_inicial;
	
	Relatorio rel = new Relatorio();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaInicial window = new telaInicial();
					window.frame_inicial.setVisible(true);
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
		frame_inicial = new JFrame("Sistema");
		frame_inicial.setBounds(100, 100, 450, 376);
		frame_inicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_inicial.getContentPane().setLayout(null);
		frame_inicial.setLocationRelativeTo(null);
		
		JLabel lblEstado = new JLabel();
		lblEstado.setBounds(241, 248, 308, 14);
		lblEstado.setText("<html><font color='red'>Nenhum arquivo selecionado</font></html>");
				
		frame_inicial.getContentPane().add(lblEstado);
		
		JButton btnSelecionarArquivo = new JButton("Selecionar Arquivo");
		btnSelecionarArquivo.setBounds(51, 244, 180, 23);
		frame_inicial.getContentPane().add(btnSelecionarArquivo);
		
		JButton btnAnalisarImagem = new JButton("Analisar Imagem");
		btnAnalisarImagem.setBounds(157, 282, 132, 23);
		frame_inicial.getContentPane().add(btnAnalisarImagem);
		
		JLabel lblFoto = new JLabel();
		lblFoto.setBounds(51, 11, 327, 207);
		frame_inicial.getContentPane().add(lblFoto);
		
		btnSelecionarArquivo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				RelatorioController r = new RelatorioController();
				File f = r.selecionarArquivo(rel);
				
				if (f.getAbsoluteFile() != null) {
					if (f.getPath() != "erro.err") {
						lblEstado.setText("<html><font color='green'>" + f.getName() + "</font></html>");
						try {
							BufferedImage bi = ImageIO.read(f);
							frame_inicial.getContentPane().add(lblFoto);
							ImageIcon imgIcon = new ImageIcon(bi.getScaledInstance(lblFoto.getWidth(),
									lblFoto.getHeight(), Image.SCALE_DEFAULT));
							lblFoto.setIcon(imgIcon);
							try {
								File arquivo = new File(f.getAbsolutePath());
								FileInputStream fis = new FileInputStream(arquivo);
								ByteArrayOutputStream bos = new ByteArrayOutputStream();
								byte[] buffer = new byte[1024];
								
								for (int i; (i=fis.read(buffer)) != -1;) {
									bos.write(buffer, 0, i);
								}
								
								byte[] foto = bos.toByteArray();
								rel.setFoto(foto);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} catch (IOException e2) {
							e2.printStackTrace();
						}	
					}				
				} else {
					lblEstado.setText("<html><font color='red'>Nenhum arquivo selecionado</font></html>");
					lblFoto.setIcon(null);
				}
			}
			
		});
		
		btnAnalisarImagem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (rel.getFoto() != null) {
					JOptionPane.showMessageDialog(null, "O sistema ir� iniciar o processamento de "
							+ "imagens.\nAguarde um instante!\nIsso pode levar alguns minutos...");
					telaAnalise ta = new telaAnalise();
					ta.setRelatorio(rel);
					ta.initialize();
					frame_inicial.setVisible(false);
					ta.frame_analise.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erro! \nSelecione um arquivo antes de continuar!");
				}
			}
			
		});
		
	}
}
