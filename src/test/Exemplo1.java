package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Exemplo1 {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat imageColorida = Imgcodecs.imread("source\\positivas_cor\\arabica-coffee-beans-tree-farm-260nw-1436268005.jpg");
		//Mat imageColorida = Imgcodecs.imread("source\\positivas_cor\\003coffee.jpg");
		//Mat imageColorida = Imgcodecs.imread("source\\5_cafe_vermelho.png");
		
		if (imageColorida.empty()) {
			System.out.println("Imagem não encontrada!");
			System.exit(0);
		}
		
		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imageColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);
		
		CascadeClassifier classificador = 
				new CascadeClassifier("source\\negativas_pb\\classificador\\cascade.xml");

		MatOfRect facesDetectadas = new MatOfRect();
		
		classificador.detectMultiScale(imageColorida, facesDetectadas, 
				1.01, 				//scale factor
				4, 					// minNeighbors
				0,					//flags
				new Size(80, 80), // minSize 
				new Size(100, 100));  //maxSize
		
		System.out.println(facesDetectadas.toArray().length);
		
		if (facesDetectadas.toArray().length > 1){
			for (Rect rect: facesDetectadas.toArray()){
				System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
				
				Imgproc.rectangle(imageColorida, new Point(rect.x, rect.y ), 
						new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
		//		i = 30;
			}
		} else {
			System.out.println("Não há faces na imagem");

		}
		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imageColorida));
	}

}