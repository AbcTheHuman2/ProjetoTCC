package controller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import model.Relatorio;

public class ComparaImagensController implements iComparaImagensController {
	
	@Override
	public void iniciarRelatorio(Relatorio r) throws Exception {
		
		//Código para gerar imagens em preto e branco
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String path = "source/negativas_cor/";
		String path2 = "source/negativas_pb/";
		List<File> fotos = new ArrayList<File>();
		
		File pasta = new File(path);
		File[] lista_arq = pasta.listFiles();
		
		for (int i = 0; i < lista_arq.length; i++) {
			if (lista_arq[i].isFile()) {
				
				fotos.add(new File(path + lista_arq[i].getName()));
				String str = fotos.get(i).toString();
				Mat orig = Imgcodecs.imread(str);
				Mat hsv = new Mat();
				
				if (orig.isContinuous()) {
					Imgproc.cvtColor(orig, hsv, Imgproc.COLOR_BGR2GRAY);
					Imgcodecs.imwrite(path2 + lista_arq[i].getName(), hsv);
				}
			}
		}
		
		System.out.println("Teste");
	}
}
