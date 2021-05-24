package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.dao.ClienteDAO;
import br.com.foursys.locadora.dao.LocacaoFilmeDAO;

/**
 * Classe para acessar o objeto Dao e efetuar altera��es e consultas na base de
 * dados
 * 
 * @author Elisso Bem merda
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class LocacaoFilmeController {

	public void salvar(LocacaoFilme locacaoFilme) {
		try {
			new LocacaoFilmeDAO().salvar(locacaoFilme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<LocacaoFilme> buscarPorFilme(String nome) {
		try {
			ArrayList<LocacaoFilme> retorno = new ArrayList<LocacaoFilme>();

			for (LocacaoFilme locacao : new LocacaoFilmeDAO().buscarTodos()) {
				String aux = locacao.getFilmeIdFilme().getNome();

				for (Filme cli : new FilmeController().buscarPorNome(nome)) {
					if (cli.getNome().equals(aux)) {
						retorno.add(locacao);
					}
				}
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<LocacaoFilme> buscarPorLocacao(Locacao locacao) {
		try {
			return new LocacaoFilmeDAO().buscarPorLocacao(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<LocacaoFilme> buscarPorCliente(String nome) {
		try {
			ArrayList<LocacaoFilme> retorno = new ArrayList<LocacaoFilme>();
			ArrayList<Cliente> aux2 = new ClienteDAO().buscarPorNome(nome);

			for (LocacaoFilme locacao : new LocacaoFilmeDAO().buscarTodos()) {
				String aux = locacao.getLocacaoIdLocacao().getClienteIdCliente().getNome();

				for (Cliente cli : aux2) {
					if (cli.getNome().equals(aux)) {
						retorno.add(locacao);
					}
				}
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * metodo para excluir um registro no bdd
	 */
	public void excluir(LocacaoFilme locacaoFilme) {
		try {
			new LocacaoFilmeDAO().excluir(locacaoFilme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<LocacaoFilme> buscarTodos() throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<LocacaoFilme> retorno = new ArrayList<LocacaoFilme>();

		try {
			retorno = new LocacaoFilmeDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}