package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.util.HibernateUtil;

public class ContatoDAO extends GenericDAO {

	@SuppressWarnings("unchecked")
	public ArrayList<Contato> buscarTodos() throws Exception {

		ArrayList<Contato> listaRetorno = new ArrayList<Contato>();
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(Contato.class);
		criteria.addOrder(Order.asc("idContato"));
		listaRetorno = (ArrayList<Contato>) criteria.list();
		sessao.close();

		return listaRetorno;
	}

	public ArrayList<Contato> buscarPorNome(String nome) throws Exception {

		ArrayList<Contato> listaRetorno = new ArrayList<Contato>();
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(Contato.class);
		criteria.add(Restrictions.like("nome", nome + "%"));
		criteria.addOrder(Order.asc("nome"));
		listaRetorno = (ArrayList<Contato>) criteria.list();
		sessao.close();

		return listaRetorno;
	}

	public Contato buscarPorCodigo(int codigo) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Contato contato = (Contato) sessao.get(Contato.class, codigo);
		sessao.close();
		return contato;
	}
	
	public ArrayList<Contato> buscarPorEstado(Estado estado) throws Exception {

		ArrayList<Contato> listaRetorno = new ArrayList<Contato>();
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(Contato.class);
		criteria.add(Restrictions.like("estadoIdEstado", estado));
		criteria.addOrder(Order.asc("nome"));
		listaRetorno = (ArrayList<Contato>) criteria.list();
		sessao.close();

		return listaRetorno;
	}

}
