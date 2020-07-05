package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import model.Relatorio;
import test.Utilitarios;

public class ComparaImagensController implements iComparaImagensController {
	
	@Override
	public Relatorio iniciarRelatorio(Relatorio r) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat imageColorida = Imgcodecs.imdecode(
				new MatOfByte(r.getFoto()), Imgcodecs.IMREAD_UNCHANGED);
		
		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imageColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);
		
		CascadeClassifier classificador = 
				new CascadeClassifier("source\\negativas_pb\\classificador\\cascade_cafe_vermelho.xml");

		MatOfRect facesDetectadas = new MatOfRect();
		List<Mat> canais = new ArrayList<Mat>();
		Core.split(imageColorida, canais);
		
		Mat copia = new Mat();
		imageColorida.copyTo(copia);
		Mat alpha = new Mat();
		
		Core.inRange(copia, new Scalar(0, 255, 0),  new Scalar(0, 179, 0), alpha);
		Core.bitwise_not(alpha, alpha);
		
		canais.add(alpha);	
		Core.merge(canais, copia);
		
		classificador.detectMultiScale(copia, facesDetectadas, 
				1.18, 				//scale factor
				2, 					// minNeighbors
				0,					//flags
				new Size(10, 10), // minSize 
				new Size(70, 70));  //maxSize
		
		Utilitarios ut = new Utilitarios();
		BufferedImage imgPixels;
		
		int frutos_vermelhos = 0;
		int frutos_totais = 0;
		int pixel;
		
		
		
		Color corDoPixel;
		
		if (facesDetectadas.toArray().length > 1){
			
			System.out.println("Cafés detectados! Filtrando frutos!");
			
			for (Rect rect: facesDetectadas.toArray()){
				
				int pixelR = 0;
				int pixelG = 0;
				int pixelB = 0;
				
				//System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
				for (int i = rect.x; i <= rect.x + rect.width; i++ ){
					for (int j = rect.y; j <= rect.y + rect.height; j++ ){
						
						imgPixels = new BufferedImage(imageColorida.cols(), imageColorida.rows(), BufferedImage.TYPE_3BYTE_BGR);
						imgPixels = ut.convertMatToImage(imageColorida);
						pixel = imgPixels.getRGB(i, j);
						corDoPixel = new Color(pixel);
						pixelR = pixelR + corDoPixel.getRed();
						pixelG = pixelG + corDoPixel.getGreen();
						pixelB = pixelB + corDoPixel.getBlue();
						
						
						//System.out.println(corDoPixel.getBlue() +"-"+ corDoPixel.getGreen() + "-" + corDoPixel.getRed());
						
						
						//System.out.println(corDoPixel);
					}
				}
				
				if(pixelR >= pixelB && pixelR >= pixelG){
					
					Imgproc.rectangle(imageColorida, new Point(rect.x, rect.y ), 
							new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
					frutos_vermelhos++;
					frutos_totais++;
				} else {
					frutos_totais++;
				}
			}
			
			int porcentagem = Math.round((100 * frutos_vermelhos)/frutos_totais);
			int porcentagem2 = 100 - porcentagem;
			r.setEh_cafe(true);
			r.setPorcentagemVermelho(porcentagem);
			r.setPorcentagemVerde(porcentagem2);
			ut.mostraImagem(ut.convertMatToImage(imageColorida));
		} else {
			System.out.println("Não há faces na imagem");
			r.setEh_cafe(false);
			r.setPorcentagemVermelho(0);
			r.setPorcentagemVerde(0);
			ut.mostraImagem(ut.convertMatToImage(imageColorida));
		}
		r.setN_frutos(frutos_totais);
		r.setFrutos_vermelhos(frutos_vermelhos);
		r.setFrutos_vermelhos(frutos_totais - frutos_vermelhos);
		return r;
	}
}
