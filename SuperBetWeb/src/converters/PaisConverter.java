package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import dominio.Pais;

public class PaisConverter implements javax.faces.convert.Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Pais pais = new Pais ();
		pais.setName(arg2);
		return pais;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Pais)arg2).getName();
	}

}
