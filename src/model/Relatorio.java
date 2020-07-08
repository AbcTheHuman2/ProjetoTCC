package model;

public class Relatorio {
	
	private int id;
	private boolean eh_cafe;
	private int n_frutos;
	private int frutos_vermelhos;
	private byte[] foto;
	private String data;
	private int porcentagemVermelho;
	private int porcentagemVerde;
	
	public int getPorcentagemVermelho() {
		return porcentagemVermelho;
	}
	
	public void setPorcentagemVermelho(int porcentagemVermelho) {
		this.porcentagemVermelho = porcentagemVermelho;
	}
	
	public int getPorcentagemVerde() {
		return porcentagemVerde;
	}
	
	public void setPorcentagemVerde(int porcentagemVerde) {
		this.porcentagemVerde = porcentagemVerde;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
	
	public int getFrutos_vermelhos() {
		return frutos_vermelhos;
	}
	
	public void setFrutos_vermelhos(int frutos_vermelhos) {
		this.frutos_vermelhos = frutos_vermelhos;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
