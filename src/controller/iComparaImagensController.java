package controller;

import model.Relatorio;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public interface iComparaImagensController {

	public Relatorio[] gerarRelatorio(Relatorio r);
}