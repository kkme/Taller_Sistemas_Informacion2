package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class BooleanConverter implements javax.faces.convert.Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Boolean fuePaga = arg2.equals("Si");
		return fuePaga;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if ((Boolean) arg2){
			return "Si";
		}
		else{
			return "No";
		}
	}

}
