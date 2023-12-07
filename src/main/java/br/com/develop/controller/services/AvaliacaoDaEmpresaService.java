package br.com.develop.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.AvaliacaoDaEmpresaDAO;
import br.com.develop.model.entities.AvaliacaoDaEmpresa;
import br.com.develop.model.utils.Transactional;

public class AvaliacaoDaEmpresaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoDaEmpresaDAO avaliacaoDaEmpresaDAO;

	@Transactional
	public void salvar(AvaliacaoDaEmpresa avaliacaoDaEmpresa) throws BusinessException {
		if (avaliacaoDaEmpresa == null) {
			throw new BusinessException("Não foi possível salvar a Avaliacao Da Empresa.");
		}
		this.avaliacaoDaEmpresaDAO.guardar(avaliacaoDaEmpresa);
	}
	
	@Transactional
	public void excluir(AvaliacaoDaEmpresa avaliacaoDaEmpresa) throws BusinessException {
		avaliacaoDaEmpresa = this.avaliacaoDaEmpresaDAO.porId(avaliacaoDaEmpresa.getId());
		if (avaliacaoDaEmpresa == null) {
			throw new BusinessException("Não é possível excluir a Avaliacao Da Empresa!");
		}
		this.avaliacaoDaEmpresaDAO.remover(avaliacaoDaEmpresa);
	}

	@Transactional
	public List<AvaliacaoDaEmpresa> pesquisar(String pesquisaAtributo, String pesquisaAtributoEspecifico, String pesquisaValor) {
		return this.avaliacaoDaEmpresaDAO.pesquisar(pesquisaAtributo, pesquisaAtributoEspecifico, pesquisaValor);
	}
}