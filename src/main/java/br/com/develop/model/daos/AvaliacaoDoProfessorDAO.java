package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.AvaliacaoDoProfessor;

public class AvaliacaoDoProfessorDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public AvaliacaoDoProfessorDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public AvaliacaoDoProfessor porId(Long id) {
		return manager.find(AvaliacaoDoProfessor.class, id);
	}
	
	public AvaliacaoDoProfessor guardar(AvaliacaoDoProfessor avaliacaoDoProfessor) {
		return this.manager.merge(avaliacaoDoProfessor);
	}
	
	public void remover(AvaliacaoDoProfessor avaliacaoDoProfessor) {
		this.manager.remove(avaliacaoDoProfessor);
	}
	
	public List<AvaliacaoDoProfessor> todas() {
		TypedQuery<AvaliacaoDoProfessor> query = manager.createQuery("FROM AvaliacaoDoProfessor", AvaliacaoDoProfessor.class);
		return query.getResultList();
	}
	
	public List<AvaliacaoDoProfessor> pesquisar(String pesquisaAtributo, String pesquisaAtributoEspecifico, String pesquisaValor) {
		
		int parametro = 1;
		switch(pesquisaAtributo){
        case "assiduidade":
            parametro = 1;
            break;
        case "disciplina":
            parametro = 2;
            break;
        case "sociabilidade":
            parametro = 3;
            break;
        case "responsabilidade":
            parametro = 4;
            break;
        case "iniciativa":
            parametro = 5;
            break;
        case "aluno":
            parametro = 6;
            break;
        case "orientador":
            parametro = 7;
            break;
		}
		
		if (parametro <= 5) {
			TypedQuery<AvaliacaoDoProfessor> queryAvaliacaoDoProfessor = manager.createQuery("FROM AvaliacaoDoProfessor e WHERE LOWER(e." + pesquisaAtributo + ") LIKE :valor", AvaliacaoDoProfessor.class);
			queryAvaliacaoDoProfessor.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
			List<AvaliacaoDoProfessor> avaliacoesDasEmpresas = queryAvaliacaoDoProfessor.getResultList();
			return avaliacoesDasEmpresas;
		} else {
			TypedQuery<AvaliacaoDoProfessor> queryAvaliacaoDoProfessor = manager.createQuery("SELECT e FROM AvaliacaoDoProfessor e JOIN FETCH e." + pesquisaAtributo + " p WHERE LOWER(p." + pesquisaAtributoEspecifico + ") LIKE :valor", AvaliacaoDoProfessor.class);
			queryAvaliacaoDoProfessor.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
            List<AvaliacaoDoProfessor> avaliacoesDasEmpresas= queryAvaliacaoDoProfessor.getResultList();
			return avaliacoesDasEmpresas;
		}
	}
}
