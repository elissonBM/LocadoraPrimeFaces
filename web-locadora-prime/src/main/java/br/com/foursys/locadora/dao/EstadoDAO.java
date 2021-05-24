package br.com.foursys.locadora.dao;

import java.util.ArrayList;
import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Estado;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.foursys.locadora.util.HibernateUtil;

/**
 * Classe responsável por armazenar os métodos para acesso ao banco de dados
 * @author Diego Munhoz
 * @since 24/03/2021
 * @version 1.0
 */
public class EstadoDAO extends GenericDAO{
   /*
    * método para consultar os cidades por estado gravados na tabela
    */
   public ArrayList<Estado> buscarTodos() throws Exception{
        // lista auxiliar para retornar no método
        ArrayList<Estado> retorno = new ArrayList<>();
        // classe auxiliar para armazenar a sessão com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        // classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(Estado.class);
        // adicionando a ordenação da pesquisa
        criteria.addOrder(Order.asc("idEstado"));
        // valorizando o objeto de retorno do método com os registros da tabela
        retorno = (ArrayList<Estado>) criteria.list();
        // encerrando a conexão com o banco de dados
        sessao.close();
        // retornando a lista preenchida
        return retorno;
    }// fim do método buscarTodos
}// fim da classe
