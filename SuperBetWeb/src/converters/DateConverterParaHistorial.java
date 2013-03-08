package converters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class DateConverterParaHistorial implements javax.faces.convert.Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		String [] fields = arg2.split("\\/");
		String [] fieldsAux = arg2.split(" ");
		String [] fields2 = fieldsAux[1].split(":"); 
		gCalendar.set(Calendar.YEAR, Integer.parseInt(fields[2]));
		gCalendar.set(Calendar.MONTH, Integer.parseInt(fields[1]) - 1);
		gCalendar.set(Calendar.DATE, Integer.parseInt(fields[0]));
		gCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(fields2[0]));
		gCalendar.set(Calendar.MINUTE, Integer.parseInt(fields2[1]));
		gCalendar.set(Calendar.SECOND, Integer.parseInt(fields2[2]));
		return gCalendar;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		GregorianCalendar gCalendar = (GregorianCalendar)arg2;
		
		String hour = Integer.toString(gCalendar.get(Calendar.HOUR_OF_DAY));
		if (gCalendar.get(Calendar.HOUR_OF_DAY) < 10){
			hour = "0" + hour;
		}
		
		String minute = Integer.toString(gCalendar.get(Calendar.MINUTE));
		if (gCalendar.get(Calendar.MINUTE) < 10){
			minute = "0" + minute;
		}
		
		String second = Integer.toString(gCalendar.get(Calendar.SECOND));
		if (gCalendar.get(Calendar.SECOND) < 10){
			second = "0" + second;
		}
		
		String date = "" + gCalendar.get(Calendar.DATE) + "/" + Integer.toString(gCalendar.get(Calendar.MONTH) + 1) + "/" + gCalendar.get(Calendar.YEAR)
					  + " " + hour + ":" + minute + ":" + second;	
		return date;
	}

}
