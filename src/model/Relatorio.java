package model;

public class Relatorio extends ATORelatorio implements ITORelatorio {

	@Override
	public Relatorio gerarRelatorio() {
		return null;
	}

	@Override
	public boolean mudarEstado() {
		return true;
	}

	@Override
	public void selecionarArquivo() {
		mudarEstado();
	}

}