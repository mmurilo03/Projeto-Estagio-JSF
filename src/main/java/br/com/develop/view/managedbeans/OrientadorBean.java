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
import br.com.develop.controller.services.OrientadorService;
import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Orientador;

@Named
@ViewScoped
public class OrientadorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrientadorService orientadorService;
	
	@Inject
	private OrientadorDAO orientadorDAO;
	
	private Orientador orientador = new Orientador();
	

	private String pesquisaAtributo;
	private String pesquisaValor;
	
	private List<Orientador> orientadores = new ArrayList<>();

	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public void salvar() {
		try {
			this.orientadorService.salvar(this.orientador);
			this.orientador = new Orientador();
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

	public String getPesquisaValor() {
		return pesquisaValor;
	}

	public void setPesquisaValor(String pesquisaValor) {
		this.pesquisaValor = pesquisaValor;
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
	
	@PostConstruct
	public void init() {
		this.orientadores = orientadorDAO.todas();
	}
	
	public List<Orientador> getOrientadores() {
		return orientadores;
	}

	public void setOrientadores(List<Orientador> orientadores) {
		this.orientadores = orientadores;
	}

	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Orientador orientador = orientadorDAO.porId(Long.parseLong(id));
		sessionMap.put("editarOrientador", orientador);
		
		return "/editar-orientador.xhtml?faces-redirect=true";
	}
 
	public String atualizar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> params = context.getSessionMap();
		
		Orientador orientador = (Orientador) params.get("editarOrientador");
		
		try {
			this.orientadorService.salvar(orientador);
		} catch (Exception e) {
		}
		
		return "/cadastro-orientador.xhtml?faces-redirect=true";
	}
	
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Orientador orientador = orientadorDAO.porId(Long.parseLong(id));
		if (orientador.getQtdAlunos() == 0) {
			try {
				this.orientadorService.excluir(orientador);
			} catch (BusinessException e) {
				e.printStackTrace();
			}			
		}
		
		return "/cadastro-orientador.xhtml?faces-redirect=true";
	}
	
	public String pesquisar() {		
		List<Orientador> orientadores = orientadorService.pesquisar(this.pesquisaAtributo, this.pesquisaValor);
		sessionMap.put("orientadoresPesquisados", orientadores);
		
		return "/pesquisa-orientador.xhtml?faces-redirect=true";
	}
	
}
