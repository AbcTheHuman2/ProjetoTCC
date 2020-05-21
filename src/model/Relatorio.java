package model;

import javax.swing.Icon;

public class Relatorio {
	
	private boolean estado;
	private Icon imagem;
	private boolean eh_cafe;
	private int n_frutos;
	private int frutos_verdes;
	
	public boolean isEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public Icon getImagem() {
		return imagem;
	}
	
	public void setImagem(Icon imagem) {
		this.imagem = imagem;
	}
	
	public boolean isEh_cafe() {
		return eh_cafe;
	}
	
	public void setEh_cafe(boolean eh_cafe) {
		this.eh_cafe = eh_cafe;
	}

	public int getN_frutos() {
		return n_frutos;
	}

	public void setN_frutos(int n_frutos) {
		this.n_frutos = n_frutos;
	}

	public int getFrutos_verdes() {
		return frutos_verdes;
	}

	public void setFrutos_verdes(int frutos_verdes) {
		this.frutos_verdes = frutos_verdes;
	}
}
