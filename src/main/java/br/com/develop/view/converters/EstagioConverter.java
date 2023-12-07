package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.EstagioDAO;
import br.com.develop.model.entities.Estagio;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = Estagio.class)
public class EstagioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) return null;
		Estagio estagio = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				EstagioDAO estagioDAO = new EstagioDAO(manager);
				estagio = estagioDAO.porId(new Long(value));
			}
			return estagio;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) return null;
		
		Estagio estagio = (Estagio) value;
		return estagio.getId().toString();
	}

}
