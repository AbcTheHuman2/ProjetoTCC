package model;

import java.io.File;

public interface ITORelatorio {

	public Relatorio gerarRelatorio();
	public File selecionarArquivo(Relatorio r);
	
}