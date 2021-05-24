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
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulos;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe responsavel por controlar os componentes do front-end Funcionario
 * 
 * @author Elisson Machado
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "funcionarioBacking")
@SessionScoped
public class FuncionarioBacking implements Serializable {

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
	private String login;
	private String senha;
	private String perfilAcesso;

	// atributos da tela de consulta
	private String nomePesquisar;
	private Funcionario funcionarioSelecionado;

	// atributos auxiliares
	private Funcionario funcionario;
	private ArrayList<Funcionario> listaFuncionarios;
	private ArrayList<String> listaPerfilAcesso;

	private Endereco endereco;
	private Contato contato;

	private boolean comboCidade = true;

	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<String> listaLogradouro;
	

	public FuncionarioBacking() {
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(String perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public ArrayList<String> getListaPerfilAcesso() {
		return listaPerfilAcesso;
	}

	public void setListaPerfilAcesso(ArrayList<String> listaPerfilAcesso) {
		this.listaPerfilAcesso = listaPerfilAcesso;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-funcionario.jsp
	 */
	public void cadastrar() {

		if (validar()) {

			try {
				getFuncionario();
				getContato();
				getEndereco();

				new ContatoController().salvar(contato);

				funcionario.setContatoIdContato(contato);

				new EnderecoController().salvar(endereco);

				funcionario.setEnderecoIdEndereco(endereco);

				new FuncionarioController().salvar(funcionario);

				limparCampos();
				JSFUtil.addInfoMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}

		}

	}

	public void alterarFuncionario() {

		if (validar()) {

			try {
				getFuncionarioAlterar();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);

				limparCampos();
				JSFUtil.addInfoMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}

		}

	}

	private boolean validar() {

		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.CPF_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.RG_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}

		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.IDADE_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.IDADE_INVALIDO);
			return false;
		}

		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.SEXO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(logradouro)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}

		if (Valida.isIntZero(numero)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_VAZIO);
			return false;
		}

//		if (Valida.isEmptyOrNull(bairro)) {
//			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.BAIRRO_VAZIO);
//			return false;
//		}

		if (Valida.isEmptyOrNull(cep)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.CEP_VAZIO);
			return false;
		}

		if (Valida.isIntZero(cidade)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(uf)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(celular)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.CELULAR_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(email)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.EMAIL_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.LOGIN_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.SENHA_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(perfilAcesso)) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.PERFIL_ACESSO_VAZIO);
			return false;
		}

		return true;
	}

	/*
	 * m�todo que captura a a��o do bot�o CANCELAR na tela cad-funcionario.jsp
	 */
	public void cancelar() {
		limparCampos();

	}

	/*
	 * m�todo que captura a a��o do bot�o SAIR na tela cad-funcionario.jsp
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-funcionario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * m�todo para abrir a tela de cadastro
	 */
	public String cadastroFuncionario() {
		limparCampos();
		return "";
	}

	/*
	 * m�todo para abrir a tela de consulta
	 */
	public String consultarFuncionario() {
		nomePesquisar = null;
		listaFuncionarios = null;
		return "";
	}

	/*
	 * m�todo para retornar um objeto Funcionario
	 */
	private void getFuncionario() {
		funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(getDateToString());
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);

		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(perfilAcesso);
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
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

	public void carregarPerfilAcesso() {
		listaPerfilAcesso = new ArrayList<String>();

		for (Perfil p : Perfil.values()) {
			listaPerfilAcesso.add(p.getPerfil());
		}
	}

	private void getFuncionarioAlterar() {
		funcionario = funcionarioSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		funcionario.setEnderecoIdEndereco(endereco);
		funcionario.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		endereco = funcionario.getEnderecoIdEndereco();
		endereco.setTipoLogradouro(logradouro);
		endereco.setEndereco(enderecoAux);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = funcionario.getContatoIdContato();
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
		setLogin(null);
		setSenha(null);
		setPerfilAcesso(null);
	}

	/*
	 * m�todo para pesquisar funcionarios
	 */
	public String pesquisar() {

		try {
			listaFuncionarios = new FuncionarioController().buscarPorNome(nomePesquisar);
			
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.FILME_ERRO_PESQUISAR);
		}

		return "";

	}

	public void detalhar() throws ParseException {
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(funcionarioSelecionado.getDataNascimento()).getTime());
		;

		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		carregarEstado();
		carregarCidadesAlterar();
		logradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero();
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		uf = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("consult-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void alterar() throws ParseException {
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(funcionarioSelecionado.getDataNascimento()).getTime());
		;

		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		carregarEstado();
		carregarCidadesAlterar();
		logradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero();
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		uf = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void excluir() {

		try {
			new FuncionarioController().excluir(funcionarioSelecionado);
			new EnderecoController().excluir(endereco);
			new ContatoController().excluir(contato);
			pesquisar();
			JSFUtil.addInfoMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulos.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_EXCLUIR);
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
