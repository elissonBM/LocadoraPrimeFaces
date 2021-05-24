package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "loginBacking")
@SessionScoped
public class LoginBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;

	public boolean user;
	public boolean adm;
	public boolean dev;

	public static Funcionario funcionarioLogado;

	public boolean isAdm() {
		return adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public boolean isDev() {
		return dev;
	}

	public void setDev(boolean dev) {
		this.dev = dev;
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public static void setFuncionarioLogado(Funcionario funcionarioLogado) {
		LoginBacking.funcionarioLogado = funcionarioLogado;
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

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public void efetuarLogin() {
		if (validar()) {

			try {
				ArrayList<Funcionario> funcionarios = new FuncionarioController().buscarPorLogin(login);
				// verifica e logo após abre o index
				if (validaDados(funcionarios)) {
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}

				}else {
					JSFUtil.addErrorMessage("Login", "Credenciais inválidas!");

				}
			} catch (Exception e) {
				JSFUtil.addErrorMessage("Login", "Credenciais inválidas!");
			}

		}

	}

	private boolean validar() {
		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage("Login", "Credenciais inválidas!");
			return false;
		}
		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage("Login", "Credenciais inválidas!");
			return false;
		}

		return true;
	}

	private boolean validaDados(ArrayList<Funcionario> func) {

		for (Funcionario funcionario : func) {
			if (funcionario.getSenha().equals(senha)) {
				funcionarioLogado = funcionario;
				if (funcionario.getPerfilAcesso().equals(Perfil.USER.getPerfil())) {
					user = true;
					adm = false;
					dev = false;
				} else if (funcionario.getPerfilAcesso().equals(Perfil.ADMIN.getPerfil())) {
					adm = true;
					dev = false;
					user = false;
				} else {
					dev = true;
					user = false;
					adm = false;
				}
				return true;
			}
		}
		return false;
	}

}
