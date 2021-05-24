package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulos;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe responsavel por controlar os componentes do front-end Cliente
 * 
 * @author Elisson Machado
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "clienteBacking")
@SessionScoped
public class ClienteBacking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// atributos da tela de cadastro
	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private String idade;
	private String sexo;
	private String logradouro;
	private String enderecoAux;
	private int numero;
	private String complemento;
	private String bairro;
	private String cep;
	private int cidade;
	private int uf;
	private String telefone;
	private String celular;
	private String email;

	// atributos da tela de consulta
	private String nomePesquisar;
	private Cliente clienteSelecionado;

	// atributos auxiliares
	private Cliente cliente;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<String> listaPerfilAcesso;

	private Endereco endereco;
	private Contato contato;

	private boolean comboCidade = true;

	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<String> listaLogradouro;
	

	public ClienteBacking() {
		carregarEstado();
		carregarPerfilAcesso();
		carregarLogradouro();
		limparCampos();
	}

	public ArrayList<String> getListaLogradouro() {
		return listaLogradouro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public boolean isComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(boolean comboCidade) {
		this.comboCidade = comboCidade;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEnderecoAux() {
		return enderecoAux;
	}

	public void setEnderecoAux(String enderecoAux) {
		this.enderecoAux = enderecoAux;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public int getUf() {
		return uf;
	}

	public void setUf(int uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<String> getListaPerfilAcesso() {
		return listaPerfilAcesso;
	}

	public void setListaPerfilAcesso(ArrayList<String> listaPerfilAcesso) {
		this.listaPerfilAcesso = listaPerfilAcesso;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public void setListaLogradouro(ArrayList<String> listaLogradouro) {
		this.listaLogradouro = listaLogradouro;
	}

	/*
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-cliente.jsp
	 */
	public void cadastrar() {

		if (validar()) {

			try {
				getCliente();
				getContato();
				getEndereco();

				new ContatoController().salvar(contato);

				cliente.setContatoIdContato(contato);

				new EnderecoController().salvar(endereco);

				cliente.setEnderecoIdEndereco(endereco);

				new ClienteController().salvar(cliente);

				limparCampos();
				JSFUtil.addInfoMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}

		}

	}

	public void alterarCliente() {

		if (validar()) {

			try {
				getClienteAlterar();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);

				limparCampos();
				JSFUtil.addInfoMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}

		}

	}

	private boolean validar() {

		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.NOME_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CPF_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.RG_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}

		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.IDADE_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.IDADE_INVALIDO);
			return false;
		}

		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.SEXO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(logradouro)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}

		if (Valida.isIntZero(numero)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.NUMERO_VAZIO);
			return false;
		}

//		if (Valida.isEmptyOrNull(bairro)) {
//			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.BAIRRO_VAZIO);
//			return false;
//		}

		if (Valida.isEmptyOrNull(cep)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CEP_VAZIO);
			return false;
		}

		if (Valida.isIntZero(cidade)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CIDADE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(uf)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.NOME_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(celular)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CELULAR_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(email)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.EMAIL_VAZIO);
			return false;
		}

		return true;
	}

	/*
	 * m�todo que captura a a��o do bot�o CANCELAR na tela cad-cliente.jsp
	 */
	public void cancelar() {
		limparCampos();

	}

	/*
	 * m�todo que captura a a��o do bot�o SAIR na tela cad-cliente.jsp
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * m�todo para abrir a tela de cadastro
	 */
	public String cadastroCliente() {
		limparCampos();
		return "";
	}

	/*
	 * m�todo para abrir a tela de consulta
	 */
	public String consultarCliente() {
		nomePesquisar = null;
		listaClientes = null;
		return "";
	}

	/*
	 * m�todo para retornar um objeto Cliente
	 */
	private void getCliente() {
		cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setDataNascimento(getDateToString());
		cliente.setIdade(Integer.parseInt(idade));
		cliente.setSexo(sexo);

	}

	private void getEndereco() {
		endereco = new Endereco();
		endereco.setTipoLogradouro(logradouro);

		endereco.setEndereco(enderecoAux);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));

	}

	private void getContato() {
		contato = new Contato();

		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}

	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public ArrayList<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(ArrayList<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	private void carregarEstado() {
		try {

			listaEstados = new EstadoController().buscarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carregarCidade() {
		try {
			if (uf > 0) {
				listaCidades = new CidadeController().buscarPorEstado(new Estado(uf));
				comboCidade = false;
			} else {
				comboCidade = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carregarCidadesAlterar() {
		listaCidades = new ArrayList<Cidade>();
		try {
			listaCidades = new CidadeController().buscarTodos();
			comboCidade = false;
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

	public void carregarPerfilAcesso() {
		listaPerfilAcesso = new ArrayList<String>();

		for (Perfil p : Perfil.values()) {
			listaPerfilAcesso.add(p.getPerfil());
		}
	}

	private void getClienteAlterar() {
		cliente = clienteSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		cliente.setEnderecoIdEndereco(endereco);
		cliente.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		endereco = cliente.getEnderecoIdEndereco();
		endereco.setTipoLogradouro(logradouro);
		endereco.setEndereco(enderecoAux);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = cliente.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);

	}

	private void limparCampos() {
		setNome(null);
		setCpf(null);
		setRg(null);
		setDataNascimento(null);
		setIdade(null);
		setSexo(null);
		setLogradouro(null);
		setNumero(0);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setCidade(0);
		setUf(0);
		setTelefone(null);
		setCelular(null);
		setEmail(null);
	}

	/*
	 * m�todo para pesquisar clientes
	 */
	public String pesquisar() {

		try {
			listaClientes = new ClienteController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.FILME_ERRO_PESQUISAR);
		}

		return "";

	}

	public void alterar() throws ParseException {
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(clienteSelecionado.getDataNascimento()).getTime());
		;

		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();
		carregarEstado();
		carregarCidadesAlterar();
		logradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero();
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		uf = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		email = clienteSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-cliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void detalhar() throws ParseException {
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(clienteSelecionado.getDataNascimento()).getTime());
		;

		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();
		carregarEstado();
		carregarCidadesAlterar();
		logradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero();
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		uf = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		email = clienteSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("consult-cliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}				
		
	}
	
	public void excluir() {

		try {
			new ClienteController().excluir(clienteSelecionado);
			new EnderecoController().excluir(endereco);
			new ContatoController().excluir(contato);
			pesquisar();
			JSFUtil.addInfoMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CLIENTE_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_EXCLUIR);
		}
	}

	/*
	 * método para carregar a lista de logradouro
	 */
	public void carregarLogradouro() {
		listaLogradouro = new ArrayList<String>();

		for (Logradouro l : Logradouro.values()) {
			listaLogradouro.add(l.getTipoLogradouro());
		}
	}
	
	
	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(dataNascimento);
	}
}
