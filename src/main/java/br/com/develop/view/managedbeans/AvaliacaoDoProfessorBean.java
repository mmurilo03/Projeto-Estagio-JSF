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
import br.com.develop.controller.services.AvaliacaoDoProfessorService;
import br.com.develop.model.daos.AvaliacaoDoProfessorDAO;
import br.com.develop.model.entities.AvaliacaoDoProfessor;

@Named
@ViewScoped
public class AvaliacaoDoProfessorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoDoProfessorService avaliacaoDoProfessorService;
	
	@Inject
	private AvaliacaoDoProfessorDAO avaliacaoDoProfessorDAO;
	
	private AvaliacaoDoProfessor avaliacaoDoProfessor = new AvaliacaoDoProfessor();
	
	private List<AvaliacaoDoProfessor> avaliacoesDosProfessores = new ArrayList<>();

	private String pesquisaAtributo;
	private String pesquisaAtributoEspecifico;
	private String pesquisaValor;
	
	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public void salvar() {
		try {
			this.avaliacaoDoProfessorService.salvar(this.avaliacaoDoProfessor);
			this.avaliacaoDoProfessor = new AvaliacaoDoProfessor();
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

	public AvaliacaoDoProfessor getAvaliacaoDoProfessor() {
		return avaliacaoDoProfessor;
	}

	public void setAvaliacaoDoProfessor(AvaliacaoDoProfessor avaliacaoDoProfessor) {
		this.avaliacaoDoProfessor = avaliacaoDoProfessor;
	}
	
	@PostConstruct
	public void init() {
		this.avaliacoesDosProfessores = avaliacaoDoProfessorDAO.todas();
	}
	
	public List<AvaliacaoDoProfessor> getAvaliacoesDosProfessores() {
		return avaliacoesDosProfessores;
	}

	public void setAvaliacoesDosProfessores(List<AvaliacaoDoProfessor> avaliacoesDosProfessores) {
		this.avaliacoesDosProfessores = avaliacoesDosProfessores;
	}

	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		AvaliacaoDoProfessor avaliacaoDoProfessor = avaliacaoDoProfessorDAO.porId(Long.parseLong(id));
		sessionMap.put("editarAvaliacaoDoProfessor", avaliacaoDoProfessor);
		
		return "/editar-avaliacaoDoProfessor.xhtml?faces-redirect=true";
	}
 
	public String atualizar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> params = context.getSessionMap();
		
		AvaliacaoDoProfessor avaliacaoDoProfessor = (AvaliacaoDoProfessor) params.get("editarAvaliacaoDoProfessor");
		
		try {
			this.avaliacaoDoProfessorService.salvar(avaliacaoDoProfessor);
		} catch (Exception e) {
		}
		
		return "/cadastro-avaliacaoDoProfessor.xhtml?faces-redirect=true";
	}
	
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		AvaliacaoDoProfessor avaliacaoDoProfessor = avaliacaoDoProfessorDAO.porId(Long.parseLong(id));
		try {
			this.avaliacaoDoProfessorService.excluir(avaliacaoDoProfessor);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return "/cadastro-avaliacaoDoProfessor.xhtml?faces-redirect=true";
	}
	public String pesquisar() {		
		List<AvaliacaoDoProfessor> avaliacoes = avaliacaoDoProfessorService.pesquisar(this.pesquisaAtributo, this.pesquisaAtributoEspecifico, this.pesquisaValor);
		sessionMap.put("avaliacoesDosProfessoresPesquisados", avaliacoes);
		
		return "/pesquisa-avaliacaoDoProfessor.xhtml?faces-redirect=true";
	}
}
