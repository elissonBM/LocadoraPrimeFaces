package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.dao.LocacaoDAO;

/**
 * Classe para acessar o objeto Dao e efetuar altera��es e consultas na base de
 * dados
 * 
 * @author Elisso Bem merda
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class LocacaoController {

	public void salvar(Locacao locacao) {
		try {
			new LocacaoDAO().salvar(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Locacao> buscarDevolvido(String devolvido) {
		try {
			return new LocacaoDAO().buscarDevolvido(devolvido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Locacao> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Locacao> retorno = new ArrayList<Locacao>();

		try {
			retorno = new LocacaoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * metodo para excluir um registro no bdd
	 */
	public void excluir(Locacao locacao) {
		try {
			new LocacaoDAO().excluir(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Locacao> buscarTodos() {
		try {
			return new LocacaoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}