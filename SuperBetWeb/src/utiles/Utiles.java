package utiles;


import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;


public class Utiles {
	
    @SuppressWarnings("deprecation")
	public static Object getSessionMBean(String expr){
    	return FacesContext.getCurrentInstance().
		getExternalContext().getSessionMap().get(expr);
    }
    
    public static void addMessage (String idComp, String messageString) {
		UIInput component = (UIInput) Utiles.findComponentInRoot(idComp);
		component.setValid(false);
		FacesMessage message = new FacesMessage(messageString);
		FacesContext.getCurrentInstance().addMessage(idComp, message);
    }
    
    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
          UIComponent root = facesContext.getViewRoot();
          component = findComponent(root, id);
        }
        return component;
    }
    
    private static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId())) return base;
        UIComponent kid = null;
        UIComponent result = null;
        Iterator kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null)) {
          kid = (UIComponent) kids.next();
          if (id.equals(kid.getId())) {
            result = kid;
            break;
          }
          result = findComponent(kid, id);
          if (result != null) {
            break;
          }
        }
        return result;
    }
}
