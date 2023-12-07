package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.AlunoDAO;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) return null;
		Aluno aluno = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				AlunoDAO alunoDAO = new AlunoDAO(manager);
				aluno = alunoDAO.porId(new Long(value));
			}
			return aluno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) return null;
		
		Aluno aluno = (Aluno) value;
		return aluno.getId().toString();
	}

}
