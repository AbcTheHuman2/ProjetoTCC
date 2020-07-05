package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;



public class TreinaOpenCV {
	public static void main(String[] args) throws Exception {
		/*List<URL> listaUrlImagens;
		for(int i = 102; i <= 200; i++) {
			listaUrlImagens = BuscadorDeImagensUtil.montarArrayDeUrls_DeSitesComuns("https://www.shutterstock.com/pt/search/frutas?page="+i, "input_html_temp"+i);
			BuscadorDeImagensUtil.buscarImagensNaWeb(listaUrlImagens, "source/temporario/", "NEGATIVAS FRUTAS X"+i);
		}*/
		
	}
	
	
	public static void treinarPositivo() {
		File folderPos = new File("source/positivas_cor/");
		File[] listaFilesPos = folderPos.listFiles();
		
		for(File file : listaFilesPos) {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			Mat imagemColorPos = Imgcodecs.imread(file.getPath());
			Mat imagemCinzaPos = new Mat();
			Imgproc.cvtColor(imagemColorPos, imagemCinzaPos, Imgproc.COLOR_BGR2GRAY);

			CascadeClassifier classificador = new CascadeClassifier("source/cascades/haarcascade_cafes_positivo.xml");
			
			MatOfRect cafesDetectados = new MatOfRect();
			
			classificador.detectMultiScale(imagemCinzaPos, cafesDetectados, 1.19, 3, 0, new Size(80,80), new Size(500, 500));
			
			//System.out.println(cafesDetectados.toArray().length);
			//System.out.println("Caminho da imagem: "+file.getPath());
			 
		}
	}
	
	public static void treinarNegativo() {
		
	}
	
	public static void redefinirTamanho(String dirOrigem, int novoTamanho) {
		File folderPos = new File(dirOrigem);
		File[] listaFilesPos = folderPos.listFiles();
		int contador = 0;
		for(File file : listaFilesPos) {
			try {
				FileInputStream inputFile = new FileInputStream(file);
				BufferedImage imageInput = ImageIO.read(inputFile);
				BufferedImage outputImage = new BufferedImage(novoTamanho, novoTamanho, imageInput.getType());
				
				Graphics2D g2d = outputImage.createGraphics();
				g2d.drawImage(imageInput, 0, 0, novoTamanho, novoTamanho, null);
		        g2d.dispose();
		        
		        String formatName = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		        ImageIO.write(outputImage, formatName, new File("source/temporario/"+file.getName()));
		        System.out.println("SUCESSO");
		        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        //ImageIO.write(resizedImage, file.getName().split(".")[1], byteArrayOutputStream);
		        
		        
		        //return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		        contador++;
		        System.out.println("Contador: "+contador);
		        
				}catch (Exception ex) {
		            // Something is going wrong while resizing image
					System.out.println("Deu Exception: "+ex+ " NO "+file.getName());
				}
		
		}
	
}
	
}
