package br.com.develop.view.managedbeans;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.controller.services.AlunoService;
import br.com.develop.model.daos.AlunoDAO;
import br.com.develop.model.entities.Aluno;

@Named
@ViewScoped
public class AlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	@Inject
	private AlunoDAO alunoDAO;
	
	private String pesquisaAtributo;
	private String pesquisaValor;
	
	private Aluno aluno = new Aluno();

	private List<Aluno> alunos = new ArrayList<>();
	
	private List<Aluno> alunosSemEstagio = new ArrayList<>();
	private List<Aluno> alunosSemAvaliacaoDoProfessor = new ArrayList<>();
	private List<Aluno> alunosSemAvaliacaoDaEmpresa = new ArrayList<>();
   
	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public void salvar() {
		try {
			this.alunoService.salvar(this.aluno);
			this.aluno = new Aluno();
			this.init();
		} catch (Exception e) {
		}
	}
	
	@PostConstruct
	public void init() {
		this.alunos = alunoDAO.todas();
		this.alunosSemEstagio = alunoDAO.alunosSemEstagio();
		this.alunosSemAvaliacaoDoProfessor = alunoDAO.alunosSemAvaliacaoDoProfessor();
		this.alunosSemAvaliacaoDaEmpresa = alunoDAO.alunosSemAvaliacaoDaEmpresa();
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Aluno> getAlunosSemEstagio() {
		return alunosSemEstagio;
	}

	public void setAlunosSemEstagio(List<Aluno> alunosSemEstagio) {
		this.alunosSemEstagio = alunosSemEstagio;
	}
	
	public List<Aluno> getAlunosSemAvaliacaoDoProfessor() {
		return alunosSemAvaliacaoDoProfessor;
	}

	public void setAlunosSemAvaliacaoDoProfessor(List<Aluno> alunosSemAvaliacaoDoProfessor) {
		this.alunosSemAvaliacaoDoProfessor = alunosSemAvaliacaoDoProfessor;
	}

	public List<Aluno> getAlunosSemAvaliacaoDaEmpresa() {
		return alunosSemAvaliacaoDaEmpresa;
	}

	public void setAlunosSemAvaliacaoDaEmpresa(List<Aluno> alunosSemAvaliacaoDaEmpresa) {
		this.alunosSemAvaliacaoDaEmpresa = alunosSemAvaliacaoDaEmpresa;
	}

	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Aluno aluno = alunoDAO.porId(Long.parseLong(id));
		sessionMap.put("editarAluno", aluno);
		
		return "/editar-aluno.xhtml?faces-redirect=true";
	}
 
	public String atualizar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> params = context.getSessionMap();
		
		Aluno aluno = (Aluno) params.get("editarAluno");
		
		try {
			this.alunoService.salvar(aluno);
		} catch (Exception e) {
		}
		
		return "/cadastro-aluno.xhtml?faces-redirect=true";
	}
	
	public String deletar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		
		Aluno aluno = alunoDAO.porId(Long.parseLong(id));
		if (aluno.getEstagio() == null) {
			try {
				this.alunoService.excluir(aluno);
			} catch (BusinessException e) {
				e.printStackTrace();
			}			
		}
		
		return "/cadastro-aluno.xhtml?faces-redirect=true";
	}
	
	public String pesquisar() {		
		List<Aluno> alunos = alunoService.pesquisar(this.pesquisaAtributo, this.pesquisaValor);
		sessionMap.put("alunosPesquisados", alunos);
		
		return "/pesquisa-aluno.xhtml?faces-redirect=true";
	}
}
