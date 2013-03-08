package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import dominio.TipoApuesta;

public class TipoApuestaConverter implements javax.faces.convert.Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		TipoApuesta tipoApuesta = new TipoApuesta();
		tipoApuesta.setName(arg2);
		return tipoApuesta;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((TipoApuesta) arg2).getName();
	}

}
