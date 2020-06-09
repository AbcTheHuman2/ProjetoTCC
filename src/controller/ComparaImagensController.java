package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

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
		
		File[] fileArray = imagens.toArray(new File[0]);
		File f = new File(tDir + "foto_principal.png");
		
		try (FileOutputStream fileOuputStream = new FileOutputStream(f)){
		    fileOuputStream.write(r.getFoto());
		 }
		
		BufferedImage img_normal = ImageIO.read(f);
		BufferedImage preto_e_branco = new BufferedImage(img_normal.getWidth(),
				img_normal.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		
		Graphics2D g2d = preto_e_branco.createGraphics();
		g2d.drawImage(img_normal, 0,0, null);
		g2d.dispose();
		
		ImageIO.write(preto_e_branco, "jpg", new File(tDir + "preto_branco.jpg"));
		
		Mat frame = Imgcodecs.imread("preto_branco.jpg");
		Mat img_baixa_qual = new Mat();
		Mat hsvImage = new Mat();
		
		//Melhorar qualidade da imagem
		Imgproc.blur(frame, img_baixa_qual, new Size(7, 7));
		
		//Converter imagem para HSV
		Imgproc.cvtColor(img_baixa_qual, hsvImage, Imgproc.COLOR_BGR2HSV);
	}
}
