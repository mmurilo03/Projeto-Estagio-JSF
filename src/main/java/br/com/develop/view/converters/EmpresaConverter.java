package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) return null;
		Empresa empresa = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				EmpresaDAO empresaDAO = new EmpresaDAO(manager);
				empresa = empresaDAO.porId(new Long(value));
			}
			return empresa;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) return null;
		
		Empresa empresa = (Empresa) value;
		return empresa.getId().toString();
	}

}
