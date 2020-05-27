package controller;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Relatorio;

public class RelatorioController implements iRelatorioController {

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
        	
        	File f = fc.getSelectedFile();
            
            if ((f.getName().endsWith(".png")) ||
            		(f.getName().endsWith(".jpg")) ||
            		(f.getName().endsWith(".jpeg"))) {
            	return f;
            } else {
            	JOptionPane.showMessageDialog(null, "Erro!\nPor favor, selecione apenas "
            			+ "um arquivo com extens�o .png, .jpg ou .jpeg!");
            	return null;
            }
        } else {
        	return null;
        }
	}

}
