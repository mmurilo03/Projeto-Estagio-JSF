package br.com.develop.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.AlunoDAO;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.utils.Transactional;

public class AlunoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AlunoDAO alunoDAO;

	@Transactional
	public void salvar(Aluno aluno) throws BusinessException {
		if (aluno == null) {
			throw new BusinessException("Não foi possível salvar a Aluno.");
		}
		this.alunoDAO.guardar(aluno);
	}
	
	@Transactional
	public void excluir(Aluno aluno) throws BusinessException {
		aluno = this.alunoDAO.porId(aluno.getId());
		if (aluno == null) {
			throw new BusinessException("Não é possível excluir a Aluno!");
		}
		this.alunoDAO.remover(aluno);
	}
	
	@Transactional
	public List<Aluno> pesquisar(String pesquisaAtributo, String pesquisaValor) {
		return this.alunoDAO.pesquisar(pesquisaAtributo, pesquisaValor);
	}

}