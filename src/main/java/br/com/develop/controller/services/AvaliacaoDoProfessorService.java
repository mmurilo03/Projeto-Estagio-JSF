package br.com.develop.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.AvaliacaoDoProfessorDAO;
import br.com.develop.model.entities.AvaliacaoDoProfessor;
import br.com.develop.model.utils.Transactional;

public class AvaliacaoDoProfessorService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoDoProfessorDAO avaliacaoDoProfessorDAO;

	@Transactional
	public void salvar(AvaliacaoDoProfessor avaliacaoDoProfessor) throws BusinessException {
		if (avaliacaoDoProfessor == null) {
			throw new BusinessException("Não foi possível salvar a AvaliacaoDoProfessor.");
		}
		this.avaliacaoDoProfessorDAO.guardar(avaliacaoDoProfessor);
	}
	
	@Transactional
	public void excluir(AvaliacaoDoProfessor avaliacaoDoProfessor) throws BusinessException {
		avaliacaoDoProfessor = this.avaliacaoDoProfessorDAO.porId(avaliacaoDoProfessor.getId());
		if (avaliacaoDoProfessor == null) {
			throw new BusinessException("Não é possível excluir a AvaliacaoDoProfessor!");
		}
		this.avaliacaoDoProfessorDAO.remover(avaliacaoDoProfessor);
	}
	@Transactional
	public List<AvaliacaoDoProfessor> pesquisar(String pesquisaAtributo, String pesquisaAtributoEspecifico, String pesquisaValor) {
		return this.avaliacaoDoProfessorDAO.pesquisar(pesquisaAtributo, pesquisaAtributoEspecifico, pesquisaValor);
	}
}