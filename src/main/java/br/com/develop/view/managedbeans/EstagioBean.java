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
import br.com.develop.controller.services.EstagioService;
import br.com.develop.model.daos.EstagioDAO;
import br.com.develop.model.entities.Estagio;

@Named
@ViewScoped
public class EstagioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EstagioService estagioService;
	
	@Inject
	private EstagioDAO estagioDAO;
	
	private Estagio estagio = new Estagio();
	

	private String pesquisaAtributo;
	private String pesquisaAtributoEspecifico;
	private String pesquisaValor;
	
	private List<Estagio> estagios = new ArrayList<>();
	
	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public void salvar() {
		try {
			this.estagioService.salvar(this.estagio);
			this.estagio = new Estagio();
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

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}
	
	@PostConstruct
	public void init() {
		this.estagios = estagioDAO.todas();
	}
	
	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}
	
	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Estagio estagio = estagioDAO.porId(Long.parseLong(id));
		sessionMap.put("editarEstagio", estagio);
		
		return "/editar-estagio.xhtml?faces-redirect=true";
	}
 
	public String atualizar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> params = context.getSessionMap();
		
		Estagio estagio = (Estagio) params.get("editarEstagio");
		
		try {
			this.estagioService.salvar(estagio);
		} catch (Exception e) {
		}
		
		return "/cadastro-estagio.xhtml?faces-redirect=true";
	}
	
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Estagio estagio = estagioDAO.porId(Long.parseLong(id));
		try {
			this.estagioService.excluir(estagio);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return "/cadastro-estagio.xhtml?faces-redirect=true";
	}
	
	public String pesquisar() {		
		List<Estagio> estagios = estagioService.pesquisar(this.pesquisaAtributo, this.pesquisaAtributoEspecifico, this.pesquisaValor);
		sessionMap.put("estagiosPesquisados", estagios);
		
		return "/pesquisa-estagio.xhtml?faces-redirect=true";
	}
	
}
