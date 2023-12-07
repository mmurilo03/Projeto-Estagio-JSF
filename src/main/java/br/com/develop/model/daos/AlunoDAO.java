package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Aluno;

public class AlunoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public AlunoDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public Aluno porId(Long id) {
		return manager.find(Aluno.class, id);
	}
	
	public Aluno guardar(Aluno aluno) {
		return this.manager.merge(aluno);
	}
	
	public void remover(Aluno aluno) {
		this.manager.remove(aluno);
	}
	
	public List<Aluno> todas() {
		TypedQuery<Aluno> query = manager.createQuery("FROM Aluno", Aluno.class);
		return query.getResultList();
	}
	
	public List<Aluno> alunosSemEstagio() {
		List<Aluno> alunos = this.todas();
		List<Aluno> alunosSemEstagio = new ArrayList<>();
		
		for(Aluno aluno : alunos) {
			if (aluno.getEstagio() == null && aluno.getEmpresaAluno() != null && aluno.getOrientadorAluno() != null) alunosSemEstagio.add(aluno);
		}
		return alunosSemEstagio;
	}
	
	public List<Aluno> alunosSemAvaliacaoDoProfessor() {
		List<Aluno> alunos = this.todas();
		List<Aluno> alunosSemAvaliacaoDoProfessor = new ArrayList<>();
		
		for(Aluno aluno : alunos) {
			if (aluno.getAvaliacaoDoProfessor() == null) alunosSemAvaliacaoDoProfessor.add(aluno);
		}
		return alunosSemAvaliacaoDoProfessor;
	}
	
	public List<Aluno> alunosSemAvaliacaoDaEmpresa() {
		List<Aluno> alunos = this.todas();
		List<Aluno> alunosSemAvaliacaoDaEmpresa = new ArrayList<>();
		
		for(Aluno aluno : alunos) {
			if (aluno.getAvaliacaoDaEmpresa() == null) alunosSemAvaliacaoDaEmpresa.add(aluno);
		}
		return alunosSemAvaliacaoDaEmpresa;
	}
	
	public List<Aluno> pesquisar(String pesquisaAtributo, String pesquisaValor) {
		TypedQuery<Aluno> queryAluno = manager.createQuery("FROM Aluno a WHERE LOWER(a." + pesquisaAtributo + ") LIKE :valor", Aluno.class);
        queryAluno.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
        List<Aluno> alunos = queryAluno.getResultList();
        return alunos;
	}
}
