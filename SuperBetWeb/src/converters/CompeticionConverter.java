package converters;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import managedbeans.ColeccionesBean;

import dominio.Competicion;
import dominio.Deporte;

public class CompeticionConverter implements javax.faces.convert.Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Competicion competicion = new Competicion();
		competicion.setNombre(arg2);
		return competicion;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Competicion)arg2).getNombre();
	}

}
