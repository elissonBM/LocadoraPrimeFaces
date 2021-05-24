package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.dao.ContatoDAO;

public class ContatoController {
    /*
    método para incluir ou alterar um objeto no banco de dados 
    */
    public void salvar(Contato contato){
        try {
             new ContatoDAO().salvar(contato);
             
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    /*
    metodo para excluir um objeto
    */
    public void excluir(Contato contato) {
        try {
            new ContatoDAO().excluir(contato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
