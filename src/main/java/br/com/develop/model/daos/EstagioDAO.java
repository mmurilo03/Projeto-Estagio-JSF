package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Estagio;

public class EstagioDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public EstagioDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public Estagio porId(Long id) {
		return manager.find(Estagio.class, id);
	}
	
	public Estagio guardar(Estagio estagio) {
		return this.manager.merge(estagio);
	}
	
	public void remover(Estagio estagio) {
		this.manager.remove(estagio);
	}
	
	public List<Estagio> todas() {
		TypedQuery<Estagio> query = manager.createQuery("FROM Estagio", Estagio.class);
		return query.getResultList();
	}
	
	public List<Estagio> pesquisar(String pesquisaAtributo, String pesquisaAtributoEspecifico, String pesquisaValor) {
		
		int parametro = 1;
		switch(pesquisaAtributo){
        case "dataInicio":
            parametro = 1;
            break;
        case "dataFim":
            parametro = 2;
            break;
        case "cargaHoraria":
            parametro = 3;
            break;
        case "status":
            parametro = 4;
            break;
        case "alunoEstagio":
            parametro = 5;
            break;
        case "orientadorEstagio":
            parametro = 6;
            break;
        case "empresaEstagio":
            parametro = 7;
            break;
		}
		
		if (parametro <= 4) {
			TypedQuery<Estagio> queryEstagio = manager.createQuery("FROM Estagio e WHERE LOWER(e." + pesquisaAtributo + ") LIKE :valor", Estagio.class);
			queryEstagio.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
			List<Estagio> estagios = queryEstagio.getResultList();
			return estagios;
		} else {
			TypedQuery<Estagio> queryEstagio = manager.createQuery("SELECT e FROM Estagio e JOIN FETCH e." + pesquisaAtributo + " p WHERE LOWER(p." + pesquisaAtributoEspecifico + ") LIKE :valor", Estagio.class);
            queryEstagio.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
            List<Estagio> estagios = queryEstagio.getResultList();
			return estagios;
		}
	}
}
