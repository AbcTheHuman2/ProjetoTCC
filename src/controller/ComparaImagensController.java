package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import model.Relatorio;

public class ComparaImagensController implements iComparaImagensController {
	
	String nome_arq = "";
	File arq = new File(nome_arq);
	
	@Override
	public void validarRelatorio(Relatorio r) throws Exception {
		
		List<URL> sites = new ArrayList<URL>();
		List<File> imagens = new ArrayList<File>();
		
		//A URL abaixo possui uma lista de URLs de fotos de frutos de café
		URL url = new URL("http://image-net.org/api/text/imagenet.synset.geturls?wnid=n12663023");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		//Listando URLs
		String aux = "";
		URL url_aux = null;
		
		int ind = 1;
		
		while (((aux = br.readLine()) != null) && (ind <= 30)) {
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
		
		arq = converterImagem(r.getFoto());
		
		Mat mat;
	}

	@Override
	public File converterImagem(byte[] foto) throws FileNotFoundException, IOException {
		
		try {
			OutputStream os = new FileOutputStream(arq);
			os.write(foto);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arq;
	}
}
