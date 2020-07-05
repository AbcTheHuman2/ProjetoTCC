package controller;

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
		
		Core.inRange(copia, new Scalar(75, 151, 75),  new Scalar(0, 255, 0), alpha);
		Core.bitwise_not(alpha, alpha);
		
		canais.add(alpha);	
		Core.merge(canais, copia);
		
		classificador.detectMultiScale(copia, facesDetectadas, 
				1.18, 				//scale factor
				3, 					// minNeighbors
				0,					//flags
				new Size(10, 10), // minSize 
				new Size(70, 70));  //maxSize
		
		//System.out.println(facesDetectadas.toArray().length);
		int frutos = 0;
		
		if (facesDetectadas.toArray().length > 1){
			for (Rect rect: facesDetectadas.toArray()){
				//System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
				
				Imgproc.rectangle(imageColorida, new Point(rect.x, rect.y ), 
						new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
				frutos++;
			}
			r.setEh_cafe(true);
			Utilitarios ut = new Utilitarios();
			ut.mostraImagem(ut.convertMatToImage(imageColorida));
		} else {
			System.out.println("Não há faces na imagem");
			r.setEh_cafe(false);
			Utilitarios ut = new Utilitarios();
			ut.mostraImagem(ut.convertMatToImage(imageColorida));
		}
		r.setN_frutos(frutos);
		
		return r;
	}
}
