package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.AvaliacaoDoProfessorDAO;
import br.com.develop.model.entities.AvaliacaoDoProfessor;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = AvaliacaoDoProfessor.class)
public class AvaliacaoDoProfessorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) return null;
		AvaliacaoDoProfessor avaliacaoDoProfessor = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				AvaliacaoDoProfessorDAO avaliacaoDoProfessorDAO = new AvaliacaoDoProfessorDAO(manager);
				avaliacaoDoProfessor = avaliacaoDoProfessorDAO.porId(new Long(value));
			}
			return avaliacaoDoProfessor;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) return null;
		
		AvaliacaoDoProfessor avaliacaoDoProfessor = (AvaliacaoDoProfessor) value;
		return avaliacaoDoProfessor.getId().toString();
	}

}
