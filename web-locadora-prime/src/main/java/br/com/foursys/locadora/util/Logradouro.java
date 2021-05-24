package br.com.foursys.locadora.util;

/**
 * ENUM responsável por asmazenar os logradouros
 * @author Lsão
 * @since 29 de abr. de 2021
 * @version 1.0 
 */
public enum Logradouro {
	

	
	AEROPORTO("Aeroporto"),
	ALAMEDA("Alameda"),
	AREA("Área"),
	AVENIDA("Avenida"),
	CAMPO("Campo"),
	CHACARA("Chácara"),
	COLONIA("Colônia"),
	CONDOMINIO("Condomínio"),
	CONJUNTO("Conjunto"),
	DISTRITO("Distrito"),
	ESPLANADA("Esplanada"),
	ESTACAO("Estação"),
	ESTRADA("Estrada"),
	FAVELA("Favela"),
	FAZENDA("Fazenda"),
	FEIRA("Feira"),
	JARDIM("Jardim"),
	LADEIRA("Ladeira"),
	LAGO("Lago"),
	LAGOA("Lagoa"),
	LARGO("Largo"),
	LOTEAMENTO("Loteamento"),
	MORRO("Morro"),
	NUCLEO("Núcleo"),
	PARQUE("Parque"),
	PASSARELA("Passarela"),
	PATIO("Pátio"),
	PRACA("Praça"),
	QUADRA("Quadra"),
	RECANTE("Recanto"),
	RESIDENCIAL("Residencial"),
	RODOVIA("Rodovia"),
	RUA("Rua"),
	SETOR("Setor"),
	SITIO("Sítio"),
	TRAVESSA("Travessa"),
	TRECHO("Trecho"),
	TREVO("Trevo"),
	VALE("Vale"),
	VEREDA("Vereda"),
	VIA("Via"),
	VIADUTO("Viaduto"),
	VIELA("Viela"),
	VILA("Vila");
	
	
	private String tipoLogradouro;
	
	Logradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	
	
	
	
	
	
	
}
