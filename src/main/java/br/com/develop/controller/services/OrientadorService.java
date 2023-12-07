package br.com.develop.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Orientador;
import br.com.develop.model.utils.Transactional;

public class OrientadorService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrientadorDAO orientadorDAO;

	@Transactional
	public void salvar(Orientador orientador) throws BusinessException {
		if (orientador == null) {
			throw new BusinessException("Não foi possível salvar a Orientador.");
		}
		this.orientadorDAO.guardar(orientador);
	}
	
	@Transactional
	public void excluir(Orientador orientador) throws BusinessException {
		orientador = this.orientadorDAO.porId(orientador.getId());
		if (orientador == null) {
			throw new BusinessException("Não é possível excluir a Orientador!");
		}
		this.orientadorDAO.remover(orientador);
	}
	@Transactional
	public List<Orientador> pesquisar(String pesquisaAtributo, String pesquisaValor) {
		return this.orientadorDAO.pesquisar(pesquisaAtributo, pesquisaValor);
	}
}