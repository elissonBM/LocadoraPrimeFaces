/**
 * 
 */
package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.dao.ClienteDAO;

/**
 * Classe responsavel por acessar o objeto DAO E efetuar a�tera��es e consultas
 * na base de dados
 * 
 * @author Elisson Machado
 * @since 27/04/2021
 * @version 1.0
 *
 */
public class ClienteController {

	public void salvar(Cliente cliente) {
		try {
			new ClienteDAO().salvar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void excluir(Cliente cliente) {
		try {
			new ClienteDAO().excluir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Cliente> retorno = new ArrayList<Cliente>();

		try {
			retorno = new ClienteDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public ArrayList<Cliente> buscarTodos() throws Exception {
		ArrayList<Cliente> retorno = new ArrayList<Cliente>();

		try {
			retorno = new ClienteDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
