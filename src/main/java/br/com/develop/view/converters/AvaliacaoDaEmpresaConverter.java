package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.AvaliacaoDaEmpresaDAO;
import br.com.develop.model.entities.AvaliacaoDaEmpresa;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = AvaliacaoDaEmpresa.class)
public class AvaliacaoDaEmpresaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) return null;
		AvaliacaoDaEmpresa avaliacaoDaEmpresa = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				AvaliacaoDaEmpresaDAO avaliacaoDaEmpresaDAO = new AvaliacaoDaEmpresaDAO(manager);
				avaliacaoDaEmpresa = avaliacaoDaEmpresaDAO.porId(new Long(value));
			}
			return avaliacaoDaEmpresa;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) return null;
		
		AvaliacaoDaEmpresa avaliacaoDaEmpresa = (AvaliacaoDaEmpresa) value;
		return avaliacaoDaEmpresa.getId().toString();
	}

}
