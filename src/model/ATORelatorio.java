package model;

import java.io.File;

public abstract class ATORelatorio {
	
	private boolean estado;
	private File foto;
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public File getFoto() {
		return foto;
	}
	
	public void setFoto(File foto) {
		this.foto = foto;
	}
}
