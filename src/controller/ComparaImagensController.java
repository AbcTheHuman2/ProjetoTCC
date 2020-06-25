package controller;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import model.Relatorio;

public class ComparaImagensController implements iComparaImagensController {
	
	@Override
	public void iniciarRelatorio(Relatorio r) throws Exception {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String path = "source/positivas_cor/";
		String path2 = "source/positivas_hsv/";
		List<File> fotos = new ArrayList<File>();
		
		File pasta = new File(path);
		File[] lista_arq = pasta.listFiles();
		
		Scalar vermelho = new Scalar(0, 0, 255);
		Scalar verde = new Scalar(0, 255, 0);
		
		JFrame frame = new JFrame("Teste");
		frame.setBounds(100, 100, 450, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblFoto = new JLabel();
		lblFoto.setBounds(51, 11, 327, 207);
		frame.getContentPane().add(lblFoto);
		
		MatOfByte mob = new MatOfByte();
		
		for (int i = 0; i < lista_arq.length; i++) {
			if (lista_arq[i].isFile()) {
				
				fotos.add(new File(path + lista_arq[i].getName()));
				String str = fotos.get(i).toString();
				
				Mat orig = Imgcodecs.imread(str);
				Mat hsv = new Mat();
				
				Imgproc.cvtColor(orig, hsv, Imgproc.COLOR_BGR2HSV);
				Imgcodecs.imwrite(path2 + lista_arq[i].getName(), hsv);
				
				Core.inRange(orig, verde, vermelho, hsv);
				Core.bitwise_not(hsv, hsv);
			}	
		}
		
		System.out.println("Teste");
	}
}
