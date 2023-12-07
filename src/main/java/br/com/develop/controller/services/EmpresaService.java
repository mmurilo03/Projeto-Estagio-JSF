package br.com.develop.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.utils.Transactional;

public class EmpresaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaDAO empresaDAO;

	@Transactional
	public void salvar(Empresa empresa) throws BusinessException {
		if (empresa == null) {
			throw new BusinessException("Não foi possível salvar a Empresa.");
		}
		this.empresaDAO.guardar(empresa);
	}
	
	@Transactional
	public void excluir(Empresa empresa) throws BusinessException {
		empresa = this.empresaDAO.porId(empresa.getId());
		if (empresa == null) {
			throw new BusinessException("Não é possível excluir a Empresa!");
		}
		this.empresaDAO.remover(empresa);
	}
	
	@Transactional
	public List<Empresa> pesquisar(String pesquisaAtributo, String pesquisaValor) {
		return this.empresaDAO.pesquisar(pesquisaAtributo, pesquisaValor);
	}
}