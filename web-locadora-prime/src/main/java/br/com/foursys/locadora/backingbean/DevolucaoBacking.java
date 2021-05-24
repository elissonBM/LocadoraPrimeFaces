package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulos;

/**
 * Classe para controlar os atributos e métodos de devolução
 * 
 * @author Elisson Barbieri Machado
 * @since 6 de mai. de 2021
 * @version 1.0
 */

@ManagedBean(name = "devolucaoBacking")
@SessionScoped
public class DevolucaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private int locacao;
	private ArrayList<Locacao> listaLocacoes;
	private String cliente;
	private String dataLocacao;
	private String dataDevolucaoLocacao;
	private String dataAtual;
	private ArrayList<Filme> listaFilmes;
	private boolean bloqueio;
	private boolean carregado;
	private Locacao devolucao;


	public DevolucaoBacking() {
		carregarLocacoes();
	}
	
	
	public int getLocacao() {
		return locacao;
	}

	public void setLocacao(int locacao) {
		this.locacao = locacao;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	
	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public String getDataDevolucaoLocacao() {
		return dataDevolucaoLocacao;
	}

	public void setDataDevolucaoLocacao(String dataDevolucaoLocacao) {
		this.dataDevolucaoLocacao = dataDevolucaoLocacao;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public boolean isCarregado() {
		return carregado;
	}

	public void setCarregado(boolean carregado) {
		this.carregado = carregado;
	}

	public void carregarLocacao() {	
		if (locacao > 0) {
			carregarTela();
		} else {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_DEVOLUCAO, "Selecione uma locação!");
		}
	}

	public void salvar() {

			try {
				getDevolucao();
				new LocacaoController().salvar(devolucao);

				alterarFilmesLocacao();
				JSFUtil.addInfoMessage(Titulos.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_SALVO);
				cancelar();
				carregarLocacoes();
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulos.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_ERRO_SALVO);
			}
	
	}



	public void cancelar() {
		setLocacao(0);
		limparCampos();
		setBloqueio(true);
		setCarregado(false);
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void limparCampos() {
		setCliente(null);
		setDataLocacao(null);
		setDataDevolucaoLocacao(null);
		setDataAtual(null);
		listaFilmes = new ArrayList<Filme>();
	}

	private void carregarLocacoes() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido("Não");
			setBloqueio(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void carregarTela() {
		listaFilmes = new ArrayList<Filme>();
		devolucao = getLocacaoLista();
		setCliente(devolucao.getClienteIdCliente().getNome());
		setDataLocacao(devolucao.getDataLocacao());
		setDataDevolucaoLocacao(devolucao.getDataDevolucao());
		setDataAtual(getDataAtualAux());

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(devolucao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}

		setBloqueio(true);
		setCarregado(true);
	}

	private void getDevolucao() {
		devolucao.setDevolvido("Sim");
		if (!dataDevolucaoLocacao.equals(dataAtual)) {
			devolucao.setDataDevolucao(dataAtual);
		}
	}

	private void alterarFilmesLocacao() {
		for (Filme filme : listaFilmes) {
			filme.setDisponivel("Sim");

			try {
				new FilmeController().salvar(filme);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Locacao getLocacaoLista() {
		int indLocacao = listaLocacoes.indexOf(new Locacao(locacao));
		return listaLocacoes.get(indLocacao);
	}

	public void desbloqueioDevolucao() {
		if (locacao > 0) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limparCampos();
	}

	private String getDataAtualAux() {
		return new SimpleDateFormat(Constantes.FORMATO_DATA).format(new Date());
	}

	
	
}
