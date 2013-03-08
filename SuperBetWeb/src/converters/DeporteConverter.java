package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import dominio.Deporte;

public class DeporteConverter implements javax.faces.convert.Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Deporte deporte = new Deporte();
		deporte.setName(arg2);
		return deporte;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Deporte)arg2).getName();
	}

}
