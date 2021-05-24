package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.CidadeDAO;

/**
 * Classe para acessar o objeto Dao e efetuar altera��es e consultas na base de
 * dados
 * 
 * @author Elisso Bem merda
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class CidadeController {

	public void salvar(Cidade cidade) {
		try {
			new CidadeDAO().salvar(cidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cidade> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Cidade> retorno = new ArrayList<Cidade>();

		try {
			retorno = new CidadeDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * metodo para excluir um registro no bdd
	 */
	public void excluir(Cidade cidade) {
		try {
			new CidadeDAO().excluir(cidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cidade> buscarPorEstado(Estado estado) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Cidade> retorno = new ArrayList<Cidade>();

		try {
			retorno = new CidadeDAO().buscarPorEstado(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public ArrayList<Cidade> buscarTodos() throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Cidade> retorno = new ArrayList<Cidade>();

		try {
			retorno = new CidadeDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}