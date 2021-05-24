package br.com.foursys.locadora.util;

/**
 * Classe para armazenar os gêneros de filmes
 * @author Elisson Barbieri Machado
 * @since 29 de abr. de 2021
 * @version 1.0
 */
public enum Genero {
	
	ACAO("Ação"),
	ANIMACAO("Animação"),
	ARTE("Arte"),
	AVENTURA("Aventura"),
	CHANCHADA("Chanchada"),
	COMEDIA("Comédia"),
	COMEDIADEACAO("Comédia de ação"),
	COMEDIADETERROR("Comédia de terror"),
	COMEDIADRAMATICA("Comédia dramática"),
	COMEDIAROMANTICA("Comédia romântica"),
	DOCUMENTARIO("Documentário"),
	DRAMA("Drama"),
	ESPIONAGEM("Espionagem"),
	FAROESTE("Faroeste"),
	FANTASIA("Fantasia"),
	FANTASIACIENTIFICA("Fantasia científica"),
	FICCAOCIENTIFICA("Ficção científica"),
	GUERRA("Guerra"),
	MUSICAL("Musical"),
	POLICIAL("Policial"),
	PORNOGRÁFICO("Pornográfico"),
	ROMANCE("Romance"),
	SERIADO("Seriado"),
	SUSPENSE("Suspense"),
	TERROR("Terror"),
	THRILLER("Thriller");
	
	private String descricao;
	
	Genero(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
