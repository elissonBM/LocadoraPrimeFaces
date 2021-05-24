package br.com.foursys.locadora.util;

/**
 * ENUM responsável por asmazenar os perfis de filme
 * @author Lsão
 * @since 29 de abr. de 2021
 * @version 1.0 
 */
public enum Perfil {
	
	ADMIN("Administrador"),
	DEV("Desenvolvedor"),
	USER("Usuario");
	
	
	private String perfil;
	
	Perfil(String perfil) {
		this.perfil = perfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	
	
	
	
	
	
}
