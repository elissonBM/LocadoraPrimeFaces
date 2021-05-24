package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulos;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe para controlar os atributos e métodos de locação
 * 
 * @author Elisson Barbieri Machado
 * @since 5 de mai. de 2021
 * @version 1.0
 */

@ManagedBean(name = "locacaoBacking")
@SessionScoped
public class LocacaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cliente;
	private String funcionario;
	private String dataLocacao;
	private String valor;
	private int formaPagamento;
	private Locacao locacao;
	private double valorTotal;

	private ArrayList<LocacaoFilme> locacaoPesquisar;

	private ArrayList<Cliente> listaClientes;
	private ArrayList<Filme> listaFilmes;
	private Date dataDevolucao;
	private ArrayList<FormaPagamento> listaFormaPagamento;
	private ArrayList<Filme> listaFilmesLocacao;
	private ArrayList<Filme> listaFilmesPesquisado;
	private ArrayList<Locacao> listaLocacao;
	private String nomeClientePesquisar;
	private String nomeFilmePesquisar;
	private Filme filmeSelecionado;
	private Locacao locacaoSelecionada;
	private int clienteAux;
	private int filmeAux;
	private boolean bloqueio;
	private boolean devolvido;
	private int index;

	public LocacaoBacking() {
		carregarDataLocacao();
		carregarFormaPagamento();
		carregarClientes();
		carregarFilmes();
		listaFilmesLocacao = new ArrayList<Filme>();
		setValor(NumberFormat.getCurrencyInstance().format(0.0));
		setIndex(0);
	}

	public ArrayList<LocacaoFilme> getLocacaoPesquisar() {
		return locacaoPesquisar;
	}

	
	public String getNomeClientePesquisar() {
		return nomeClientePesquisar;
	}

	public void setNomeClientePesquisar(String nomeClientePesquisar) {
		this.nomeClientePesquisar = nomeClientePesquisar;
	}

	public String getNomeFilmePesquisar() {
		return nomeFilmePesquisar;
	}

	public void setNomeFilmePesquisar(String nomeFilmePesquisar) {
		this.nomeFilmePesquisar = nomeFilmePesquisar;
	}

	public void setLocacaoPesquisar(ArrayList<LocacaoFilme> locacaoPesquisar) {
		this.locacaoPesquisar = locacaoPesquisar;
	}

	
	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

	public Locacao getLocacaoSelecionada() {
		return locacaoSelecionada;
	}

	public void setLocacaoSelecionada(Locacao locacaoSelecionada) {
		this.locacaoSelecionada = locacaoSelecionada;
	}


	public ArrayList<Locacao> getListaLocacao() {
		return listaLocacao;
	}

	public void setListaLocacao(ArrayList<Locacao> listaLocacao) {
		this.listaLocacao = listaLocacao;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public ArrayList<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(ArrayList<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public ArrayList<Filme> getListaFilmesLocacao() {
		return listaFilmesLocacao;
	}

	public void setListaFilmesLocacao(ArrayList<Filme> listaFilmesLocacao) {
		this.listaFilmesLocacao = listaFilmesLocacao;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getClienteAux() {
		return clienteAux;
	}

	public void setClienteAux(int clienteAux) {
		this.clienteAux = clienteAux;
	}

	public int getFilmeAux() {
		return filmeAux;
	}

	public void setFilmeAux(int filmeAux) {
		this.filmeAux = filmeAux;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * m�todo para pesquisar filmes
	 */
	public String pesquisarPorCliente() {

		try {
			locacaoPesquisar = new LocacaoFilmeController().buscarPorCliente(nomeClientePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_PESQUISA);
		}
		return "";
	}

	public String pesquisarPorFilme() {

		try {
			locacaoPesquisar = new LocacaoFilmeController().buscarPorFilme(nomeFilmePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_PESQUISA);
		}
		return "";
	}

	public void carregarFormaPagamento() {
		try {

			listaFormaPagamento = new FormaPagamentoController().buscarTodos();
		} catch (Exception e) {
			// TODO Auto-generaelted catch block
			e.printStackTrace();
		}
	}

	public void carregarClientes() {
		try {
			listaClientes = new ClienteController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void carregarTela() {
		listaFilmes = new ArrayList<Filme>();
		setCliente(locacao.getClienteIdCliente().getNome());
		setDataLocacao(locacao.getDataLocacao());
		setValor(locacao.getValor() +"");

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(locacao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}

	}

	// ===============================================

	private void carregarFilmes() {
		try {
			listaFilmes = new FilmeController().buscarDisponivel("Sim");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remover() {
		listaFilmesLocacao.remove(filmeSelecionado);
		listaFilmes.add(filmeSelecionado);

		if (filmeSelecionado.getPromocao().equals("Sim")) {
			valorTotal -= filmeSelecionado.getValorPromocao();
		} else {
			valorTotal -= filmeSelecionado.getValor();
		}
		valor = NumberFormat.getCurrencyInstance().format(valorTotal);

	}

	private boolean validar() {

		if (Valida.isIntZero(clienteAux)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO, Mensagem.CLIENTE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(listaFilmesLocacao.size())) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO, Mensagem.FILME_VAZIO);
			return false;
		}

		if (Valida.isIntZero(formaPagamento)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO, Mensagem.FORMA_PAGAMENTO_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataDevolucao)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO, Mensagem.DATA_DEVOLUCAO_VAZIO);
			return false;
		}

		return true;
	}

	public void adicionar() {

		if (clienteAux > 0) {

			if (filmeAux > 0) {
				int index = listaFilmes.indexOf(new Filme(filmeAux));
				Filme filme = listaFilmes.get(index);

				if (filme.getFaixaEtaria() > getClienteLista().getIdade()) {
					JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO,
							"Filme não permitido para o cliente selecionado!");
				} else {
					listaFilmesLocacao.add(filme);
					listaFilmes.remove(filme);

					if (filme.getPromocao().equals("Sim")) {
						valorTotal += filme.getValorPromocao();
					} else {
						valorTotal += filme.getValor();
					}
					valor = NumberFormat.getCurrencyInstance().format(valorTotal);
				}

			} else {
				JSFUtil.addErrorMessage("Efetuar Locação", "Selecione um filme!");
			}
		} else {
			JSFUtil.addErrorMessage("Efetuar Locação", "Selecione um cliente!");
		}
	}

	private void limparCampos() {
		carregarFilmes();
		setFilmeAux(0);
		listaFilmesLocacao = new ArrayList<Filme>();
		setFormaPagamento(0);
		setDataDevolucao(null);
		valorTotal = Constantes.DOUBLE_ZERO;
		setValor(NumberFormat.getCurrencyInstance().format(Constantes.DOUBLE_ZERO));
	}

	public void cadastrar() {

		if (validar()) {

			try {
				getLocacao();
				new LocacaoController().salvar(locacao);

				salvarLocacaoFilme();

				cancelar();
				JSFUtil.addInfoMessage(Titulos.CADASTRO_LOCACAO, Mensagem.LOCACAO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulos.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_SALVO);
			}

		}

	}

	private void getLocacao() {
		locacao = new Locacao();

		locacao.setDataLocacao(dataLocacao);
		locacao.setDataDevolucao(getDateToString());

		locacao.setValor(valorTotal);
		locacao.setDevolvido("Não");

		locacao.setFuncionarioIdFuncionario(LoginBacking.funcionarioLogado);
		locacao.setFormaPagamentoIdFormaPagamento(getFormaPagamentoLista());
		locacao.setClienteIdCliente(getClienteLista());

	}

	private void salvarLocacaoFilme() {
		for (Filme filme : listaFilmesLocacao) {
			try {
				LocacaoFilme locacaoFilme = new LocacaoFilme();
				locacaoFilme.setFilmeIdFilme(filme);
				locacaoFilme.setLocacaoIdLocacao(locacao);

				new LocacaoFilmeController().salvar(locacaoFilme);

				filme.setDisponivel("Não");
				new FilmeController().salvar(filme);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void cancelar() {
		setClienteAux(0);
		setBloqueio(true);
		limparCampos();
		setIndex(0);
	}

	private void carregarDataLocacao() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat(Constantes.FORMATO_DATA);
		dataLocacao = formatador.format(data);
	}

	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat(Constantes.FORMATO_DATA);
		return format.format(dataDevolucao);
	}

	private FormaPagamento getFormaPagamentoLista() {
		int indFormaPagamento = listaFormaPagamento.indexOf(new FormaPagamento(formaPagamento));
		return listaFormaPagamento.get(indFormaPagamento);
	}

	private Cliente getClienteLista() {
		int indCliente = listaClientes.indexOf(new Cliente(clienteAux));
		return listaClientes.get(indCliente);
	}

	public void desbloqueioFilmes() {
		if (clienteAux > 0) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limparCampos();
	}

	public void proximo() {
		setIndex(1);
	}

	public void devolver() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cad-devolucao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void detalhar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("consult-locacao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
