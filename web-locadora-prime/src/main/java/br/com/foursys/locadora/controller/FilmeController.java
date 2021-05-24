package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.dao.FilmeDAO;

/**
 * Classe para acessar o objeto Dao e efetuar altera��es e consultas na base de
 * dados
 * 
 * @author Elisson Barbieri Machado
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class FilmeController {

	public void salvar(Filme filme) {
		try {
			new FilmeDAO().salvar(filme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Filme> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Filme> retorno = new ArrayList<Filme>();

		try {
			retorno = new FilmeDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * metodo para excluir um registro no bdd
	 */
	public void excluir(Filme filme) {
		try {
			new FilmeDAO().excluir(filme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Filme> buscarDisponivel(String disponivel) {
		ArrayList<Filme> retorno = new ArrayList<Filme>();
		try {
			retorno = new FilmeDAO().buscarDisponivel(disponivel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
