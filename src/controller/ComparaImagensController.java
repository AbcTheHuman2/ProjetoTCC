package controller;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import model.Relatorio;

public class ComparaImagensController implements iComparaImagensController {
	
	@Override
	public Relatorio[] gerarRelatorio(Relatorio r) {
		
		Relatorio[] relatorio = new Relatorio[1];
		return relatorio;
	}
	
}