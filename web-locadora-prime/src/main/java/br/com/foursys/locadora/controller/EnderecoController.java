package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.dao.EnderecoDAO;

public class EnderecoController {
    /*
    método para incluir ou alterar um objeto no banco de dados 
    */
    public void salvar(Endereco endereco){
        try {
             new EnderecoDAO().salvar(endereco);
             
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }
      /*
    metodo para excluir um objeto
    */
    public void excluir(Endereco endereco) {
        try {
            new EnderecoDAO().excluir(endereco);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
