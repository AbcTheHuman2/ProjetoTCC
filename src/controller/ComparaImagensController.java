package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.io.FileUtils;

import model.Relatorio;

public class ComparaImagensController implements iComparaImagensController {
	
	@Override
	public void iniciarRelatorio(Relatorio r) throws Exception {
		
		List<URL> sites = new ArrayList<URL>();
		List<File> imagens = new ArrayList<File>();
		
		//A URL abaixo possui uma lista de URLs de fotos de frutos de café
		URL url = new URL("http://image-net.org/api/text/imagenet.synset.geturls?wnid=n12663023");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		//Listando URLs
		String aux = "";
		URL url_aux = null;
		
		int ind = 1;
		
		while (((aux = br.readLine()) != null) && (ind <= 40)) {
			url_aux = new URL(aux);
			sites.add(url_aux);
			ind++;
		}
		br.close();
		
		URL[] urlArray = sites.toArray(new URL[0]);
		File arq_img = new File("");
		
		String tDir = System.getProperty("java.io.tmpdir");
		String path = "";
		
		HttpURLConnection huc; 
		
		//Fotos das URLs listadas
		for (int i = 0; i < urlArray.length; i++) {
			path = tDir + "imagem" + i + "_tmp.jpg";
			arq_img = new File(path);
			arq_img.deleteOnExit();
			
			String s = urlArray[i].toString();
			
			if (s.contains(".com") ||
					s.contains(".org") ||
					s.contains(".edu") ||
					s.contains(".jp/")) {
				huc =  (HttpURLConnection) urlArray[i].openConnection();
				huc.setRequestMethod("GET");
				huc.connect();
				int code = huc.getResponseCode();
				
				if (code == 301) {	
					FileUtils.copyURLToFile(urlArray[i], arq_img);
					imagens.add(arq_img);
				}
			}
		}
		
		JFrame teste = new JFrame("Teste");
		teste.setBounds(100, 100, 665, 418);
		teste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		teste.getContentPane().setLayout(null);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(100, 100, 665, 418);
		teste.getContentPane().add(lblFoto);
		Image img = new ImageIcon(r.getFoto()).getImage();
		
		File f = new File("");
		BufferedImage bi = ImageIO.read(f);
		ImageIcon imgIcon = new ImageIcon(bi.getScaledInstance(lblFoto.getWidth(),
				lblFoto.getHeight(), Image.SCALE_DEFAULT));
		
		lblFoto.setIcon(imgIcon);
		lblFoto.setVisible(true);
	}
}
