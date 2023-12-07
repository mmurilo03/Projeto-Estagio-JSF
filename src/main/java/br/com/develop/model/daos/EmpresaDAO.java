package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Empresa;

public class EmpresaDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public EmpresaDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}
	
	public Empresa guardar(Empresa empresa) {
		return this.manager.merge(empresa);
	}
	
	public void remover(Empresa empresa) {
		this.manager.remove(empresa);
	}
	
	public List<Empresa> todas() {
		TypedQuery<Empresa> query = manager.createQuery("FROM Empresa", Empresa.class);
		return query.getResultList();
	}
	
	public List<Empresa> pesquisar(String pesquisaAtributo, String pesquisaValor) {
		TypedQuery<Empresa> queryEmpresa = manager.createQuery("FROM Empresa e JOIN FETCH e.alunos WHERE LOWER(e." + pesquisaAtributo + ") LIKE :valor", Empresa.class);
        queryEmpresa.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
        List<Empresa> empresas = queryEmpresa.getResultList();
        return empresas;
	}
}
