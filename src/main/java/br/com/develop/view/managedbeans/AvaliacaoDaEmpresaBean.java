package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.controller.services.AvaliacaoDaEmpresaService;
import br.com.develop.model.daos.AvaliacaoDaEmpresaDAO;
import br.com.develop.model.entities.AvaliacaoDaEmpresa;

@Named
@ViewScoped
public class AvaliacaoDaEmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoDaEmpresaService avaliacaoDaEmpresaService;
	
	@Inject
	private AvaliacaoDaEmpresaDAO avaliacaoDaEmpresaDAO;
	
	private AvaliacaoDaEmpresa avaliacaoDaEmpresa = new AvaliacaoDaEmpresa();
	

	private String pesquisaAtributo;
	private String pesquisaAtributoEspecifico;
	private String pesquisaValor;
	
	private List<AvaliacaoDaEmpresa> avaliacoesDasEmpresas = new ArrayList<>();
	
	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public void salvar() {
		try {
			this.avaliacaoDaEmpresaService.salvar(this.avaliacaoDaEmpresa);
			this.avaliacaoDaEmpresa = new AvaliacaoDaEmpresa();
			this.init();
		} catch (Exception e) {
		}
	}

	public String getPesquisaAtributo() {
		return pesquisaAtributo;
	}

	public void setPesquisaAtributo(String pesquisaAtributo) {
		this.pesquisaAtributo = pesquisaAtributo;
	}

	public String getPesquisaAtributoEspecifico() {
		return pesquisaAtributoEspecifico;
	}

	public void setPesquisaAtributoEspecifico(String pesquisaAtributoEspecifico) {
		this.pesquisaAtributoEspecifico = pesquisaAtributoEspecifico;
	}



	public String getPesquisaValor() {
		return pesquisaValor;
	}



	public void setPesquisaValor(String pesquisaValor) {
		this.pesquisaValor = pesquisaValor;
	}



	public AvaliacaoDaEmpresa getAvaliacaoDaEmpresa() {
		return avaliacaoDaEmpresa;
	}

	public void setAvaliacaoDaEmpresa(AvaliacaoDaEmpresa avaliacaoDaEmpresa) {
		this.avaliacaoDaEmpresa = avaliacaoDaEmpresa;
	}
	
	@PostConstruct
	public void init() {
		this.avaliacoesDasEmpresas = avaliacaoDaEmpresaDAO.todas();
	}
	
	public List<AvaliacaoDaEmpresa> getAvaliacoesDasEmpresas() {
		return avaliacoesDasEmpresas;
	}

	public void setAvaliacoesDasEmpresas(List<AvaliacaoDaEmpresa> avaliacoesDasEmpresas) {
		this.avaliacoesDasEmpresas = avaliacoesDasEmpresas;
	}

	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		AvaliacaoDaEmpresa avaliacaoDaEmpresa = avaliacaoDaEmpresaDAO.porId(Long.parseLong(id));
		sessionMap.put("editarAvaliacaoDaEmpresa", avaliacaoDaEmpresa);
		
		return "/editar-avaliacaoDaEmpresa.xhtml?faces-redirect=true";
	}
 
	public String atualizar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> params = context.getSessionMap();
		
		AvaliacaoDaEmpresa avaliacaoDaEmpresa = (AvaliacaoDaEmpresa) params.get("editarAvaliacaoDaEmpresa");
		
		try {
			this.avaliacaoDaEmpresaService.salvar(avaliacaoDaEmpresa);
		} catch (Exception e) {
		}
		
		return "/cadastro-avaliacaoDaEmpresa.xhtml?faces-redirect=true";
	}
	
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		AvaliacaoDaEmpresa avaliacaoDaEmpresa = avaliacaoDaEmpresaDAO.porId(Long.parseLong(id));
		try {
			this.avaliacaoDaEmpresaService.excluir(avaliacaoDaEmpresa);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return "/cadastro-avaliacaoDaEmpresa.xhtml?faces-redirect=true";
	}
	
	public String pesquisar() {		
		List<AvaliacaoDaEmpresa> avaliacoes = avaliacaoDaEmpresaService.pesquisar(this.pesquisaAtributo, this.pesquisaAtributoEspecifico, this.pesquisaValor);
		sessionMap.put("avaliacoesDasEmpresasPesquisadas", avaliacoes);
		
		return "/pesquisa-avaliacaoDaEmpresa.xhtml?faces-redirect=true";
	}
}
