
package controller;

import model.Relatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public interface iComparaImagensController {
	
	public File converterImagem(byte[] foto) throws FileNotFoundException, IOException;
	public void validarRelatorio(Relatorio r) throws Exception;
}