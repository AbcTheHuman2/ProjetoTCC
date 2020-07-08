package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ComparaImagensController;
import model.Relatorio;
import javax.swing.JButton;

public class telaAnalise {
	
	public JFrame frame_analise;
	private Relatorio r;
	
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
		//initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame_analise = new JFrame("Analisando Imagem...");
		frame_analise.setBounds(100, 100, 665, 418);
		frame_analise.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_analise.getContentPane().setLayout(null);
		
		r = getRelatorio();
		
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
		lblFrutosEncontrados.setBounds(337, 106, 135, 14);
		frame_analise.getContentPane().add(lblFrutosEncontrados);
		lblFrutosEncontrados.setVisible(false);
		
		JLabel lblVlrFrutos = new JLabel("");
		lblVlrFrutos.setBounds(470, 106, 135, 14);
		frame_analise.getContentPane().add(lblVlrFrutos);
		lblVlrFrutos.setVisible(false);
		
		JLabel lblVlrFrutosVermelhos = new JLabel("");
		lblVlrFrutosVermelhos.setBounds(470, 131, 135, 14);
		frame_analise.getContentPane().add(lblVlrFrutosVermelhos);
		lblVlrFrutosVermelhos.setVisible(false);
		
		JLabel lblVlrFrutosVerdes = new JLabel("");
		lblVlrFrutosVerdes.setBounds(470, 156, 135, 14);
		frame_analise.getContentPane().add(lblVlrFrutosVerdes);
		lblVlrFrutosVerdes.setVisible(false);
		
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
		
		ComparaImagensController cic = new ComparaImagensController();
		try {
			Relatorio rel = cic.iniciarRelatorio(r);
			Integer integerVermelhos = rel.getFrutos_vermelhos();
			Integer integerVerdes = rel.getN_frutos() - rel.getFrutos_vermelhos();
			Integer integerTotal = rel.getN_frutos();
			Integer integerPorcentagemVerm = rel.getPorcentagemVermelho();
			Integer integerPorcentagemVerdes = rel.getPorcentagemVerde();
			
			if (rel.isEh_cafe()) {
				lblEstadoFoto.setText("<html><font color='green'>Foto válida!</font></html>");
				lblVlrFrutos.setText(integerTotal.toString());
				lblVlrFrutosVerdes.setText(integerVerdes.toString() + " - (" + integerPorcentagemVerdes.toString() + "%)");
				lblVlrFrutosVermelhos.setText(integerVermelhos.toString()  + " - (" + integerPorcentagemVerm.toString() + "%)");
				lblFrutosEncontrados.setVisible(true);
				lblFrutosMaduros.setVisible(true);
				lblFrutosVerdes.setVisible(true);
				lblVlrFrutos.setVisible(true);
				lblVlrFrutosVerdes.setVisible(true);
				lblVlrFrutosVermelhos.setVisible(true);
				btnMenuPrincipal.setVisible(true);
			} else {
				lblEstadoFoto.setText("<html><font color='red'>Foto inválida!</font></html>");
				btnMenuPrincipal.setVisible(true);
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		btnMenuPrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				telaInicial ti = new telaInicial();
				frame_analise.setVisible(false);
				ti.frame_inicial.setVisible(true);
			}
		});
	}

	public void setRelatorio(Relatorio rel) {
		this.r = rel;
	}
	
	public Relatorio getRelatorio() {
		return r;
	}
}
