package converters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class DateConverter implements javax.faces.convert.Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		String [] fields = arg2.split("\\/");
		gCalendar.set(Calendar.YEAR, Integer.parseInt(fields[2]));
		gCalendar.set(Calendar.MONTH, Integer.parseInt(fields[1]) - 1);
		gCalendar.set(Calendar.DATE, Integer.parseInt(fields[0]));
		gCalendar.set(Calendar.SECOND, 0);
		return gCalendar;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		GregorianCalendar gCalendar = (GregorianCalendar)arg2;
		String date = "" + gCalendar.get(Calendar.DATE) + "/" + Integer.toString(gCalendar.get(Calendar.MONTH) + 1) + "/" + gCalendar.get(Calendar.YEAR);
		return date;
	}

}
