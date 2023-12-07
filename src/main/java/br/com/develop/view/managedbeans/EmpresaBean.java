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
import br.com.develop.controller.services.EmpresaService;
import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.entities.Empresa;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaService empresaService;
	
	@Inject
	private EmpresaDAO empresaDAO;
	
	private Empresa empresa = new Empresa();
	
	private String pesquisaAtributo;
	private String pesquisaValor;
	
	private List<Empresa> empresas = new ArrayList<>();

	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public void salvar() {
		try {
			this.empresaService.salvar(this.empresa);
			this.empresa = new Empresa();
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

	@PostConstruct
	public void init() {
		this.empresas = empresaDAO.todas();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Empresa empresa = empresaDAO.porId(Long.parseLong(id));
		sessionMap.put("editarEmpresa", empresa);
		
		return "/editar-empresa.xhtml?faces-redirect=true";
	}
 
	public String atualizar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> params = context.getSessionMap();
		
		Empresa empresa = (Empresa) params.get("editarEmpresa");
		
		try {
			this.empresaService.salvar(empresa);
		} catch (Exception e) {
		}
		
		return "/cadastro-empresa.xhtml?faces-redirect=true";
	}
	
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Empresa empresa = empresaDAO.porId(Long.parseLong(id));
		if (empresa.getQtdAlunos() == 0) {
			try {
				this.empresaService.excluir(empresa);
			} catch (BusinessException e) {
				e.printStackTrace();
			}			
		}
		
		return "/cadastro-empresa.xhtml?faces-redirect=true";
	}
	public String pesquisar() {		
		List<Empresa> empresas = empresaService.pesquisar(this.pesquisaAtributo, this.pesquisaValor);
		sessionMap.put("empresasPesquisadas", empresas);
		
		return "/pesquisa-empresa.xhtml?faces-redirect=true";
	}
	
}
