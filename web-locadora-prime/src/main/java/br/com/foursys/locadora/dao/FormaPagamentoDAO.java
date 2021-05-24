package br.com.foursys.locadora.dao;

import java.util.ArrayList;
import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.FormaPagamento;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.foursys.locadora.util.HibernateUtil;

/**
 * Classe responsável por armazenar os métodos para acesso ao banco de dados
 * @author Elisson Machado
 * @since 24/03/2021
 * @version 1.0
 */
public class FormaPagamentoDAO extends GenericDAO{
   /*
    * método para consultar os cidades por estado gravados na tabela
    */
   public ArrayList<FormaPagamento> buscarTodos() throws Exception{
        // lista auxiliar para retornar no método
        ArrayList<FormaPagamento> retorno = new ArrayList<>();
        // classe auxiliar para armazenar a sessão com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        // classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(FormaPagamento.class);
        // adicionando a ordenação da pesquisa
        criteria.addOrder(Order.asc("idFormaPagamento"));
        // valorizando o objeto de retorno do método com os registros da tabela
        retorno = (ArrayList<FormaPagamento>) criteria.list();
        // encerrando a conexão com o banco de dados
        sessao.close();
        // retornando a lista preenchida
        return retorno;
    }// fim do método buscarTodos
}// fim da classe
