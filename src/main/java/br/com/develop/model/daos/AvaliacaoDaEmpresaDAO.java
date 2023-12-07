package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.AvaliacaoDaEmpresa;

public class AvaliacaoDaEmpresaDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public AvaliacaoDaEmpresaDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public AvaliacaoDaEmpresa porId(Long id) {
		return manager.find(AvaliacaoDaEmpresa.class, id);
	}
	
	public AvaliacaoDaEmpresa guardar(AvaliacaoDaEmpresa avaliacaoDaAvaliacaoDaEmpresa) {
		return this.manager.merge(avaliacaoDaAvaliacaoDaEmpresa);
	}
	
	public void remover(AvaliacaoDaEmpresa avaliacaoDaAvaliacaoDaEmpresa) {
		this.manager.remove(avaliacaoDaAvaliacaoDaEmpresa);
	}
	
	public List<AvaliacaoDaEmpresa> todas() {
		TypedQuery<AvaliacaoDaEmpresa> query = manager.createQuery("FROM AvaliacaoDaEmpresa", AvaliacaoDaEmpresa.class);
		return query.getResultList();
	}
	
	public List<AvaliacaoDaEmpresa> pesquisar(String pesquisaAtributo, String pesquisaAtributoEspecifico, String pesquisaValor) {
		
		int parametro = 1;
		switch(pesquisaAtributo){
        case "rendimentoDeTrabalho":
            parametro = 1;
            break;
        case "conhecimentos":
            parametro = 2;
            break;
        case "cumprimentoDasTarefas":
            parametro = 3;
            break;
        case "aprendizagem":
            parametro = 4;
            break;
        case "desempenho":
            parametro = 5;
            break;
        case "aluno":
            parametro = 6;
            break;
        case "empresa":
            parametro = 7;
            break;
		}
		
		if (parametro <= 5) {
			TypedQuery<AvaliacaoDaEmpresa> queryAvaliacaoDaEmpresa = manager.createQuery("FROM AvaliacaoDaEmpresa e WHERE LOWER(e." + pesquisaAtributo + ") LIKE :valor", AvaliacaoDaEmpresa.class);
			queryAvaliacaoDaEmpresa.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
			List<AvaliacaoDaEmpresa> avaliacoesDasEmpresas = queryAvaliacaoDaEmpresa.getResultList();
			return avaliacoesDasEmpresas;
		} else {
			TypedQuery<AvaliacaoDaEmpresa> queryAvaliacaoDaEmpresa = manager.createQuery("SELECT e FROM AvaliacaoDaEmpresa e JOIN FETCH e." + pesquisaAtributo + " p WHERE LOWER(p." + pesquisaAtributoEspecifico + ") LIKE :valor", AvaliacaoDaEmpresa.class);
			queryAvaliacaoDaEmpresa.setParameter("valor", "%" + pesquisaValor.toLowerCase() + "%");
            List<AvaliacaoDaEmpresa> avaliacoesDasEmpresas= queryAvaliacaoDaEmpresa.getResultList();
			return avaliacoesDasEmpresas;
		}
	}
}
