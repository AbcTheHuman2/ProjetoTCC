package model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.telaInicial;

public class Relatorio extends ATORelatorio implements ITORelatorio {

	@Override
	public Relatorio gerarRelatorio() {
		return null;
	}

	@Override
	public File selecionarArquivo(Relatorio r) {
		
		JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtroPng = new FileNameExtensionFilter(
            "Arquivo PNG (*.png)", "png");
        FileNameExtensionFilter filtroJpg = new FileNameExtensionFilter(
                "Arquivo JPEG (*.jpg, *.jpeg)", "jpg", "jpeg");
        
        fc.addChoosableFileFilter(filtroPng);
        fc.addChoosableFileFilter(filtroJpg);
        fc.setFileFilter(filtroPng);
        
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
        	r.setEstado(true);
        }
        
        File f = fc.getSelectedFile();
        return f;
	}

}