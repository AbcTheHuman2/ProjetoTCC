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
		Mat imageColorida = Imgcodecs.imread("src\\outros\\cafe1.jpg");
		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imageColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY); 

		for (int i = 1; i < 30; i++ ){
			CascadeClassifier classificador = 
					new CascadeClassifier("src\\cascades\\classificador_"+ i +"\\cascade.xml");

			MatOfRect facesDetectadas = new MatOfRect();

			classificador.detectMultiScale(imagemCinza, facesDetectadas, 
					3, 				//scale factor
					7, 					// minNeighbors
					0,					//flags
					new Size(40, 40), 	// minSize 
					new Size(100, 100));  //maxSize


				System.out.println(facesDetectadas.toArray().length);

				if (facesDetectadas.toArray().length > 1){
					for (Rect rect: facesDetectadas.toArray()){
						System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
						Imgproc.rectangle(imageColorida, new Point(rect.x, rect.y ), 
								new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
		//				i = 30;
					}
				} else {
					System.out.println("Não há faces na imagem");

				}
			}
		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imageColorida));
	}

}