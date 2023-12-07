package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Orientador;

public class OrientadorDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public OrientadorDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public Orientador porId(Long id) {
		return manager.find(Orientador.class, id);
	}
	
	public Orientador guardar(Orientador orientador) {
		return this.manager.merge(orientador);
	}
	
	public void remover(Orientador orientador) {
		this.manager.remove(orientador);
	}
	
	public List<Orientador> todas() {
		TypedQuery<Orientador> query = manager.createQuery("FROM Orientador", Orientador.class);
		return query.getResultList();
	}
	
	public List<Orientador> pesquisar(String pesquisaAtributo, String pesquisaValor) {
		TypedQuery<Orientador> queryOrientador = manager.createQuery("FROM Orientador o JOIN FETCH o.alunos WHERE LOWER(o." + pesquisaAtributo + ") LIKE :valor", Orientador.class);
        queryOrientador.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
        List<Orientador> orientadores = queryOrientador.getResultList();
        return orientadores;
	}
}
