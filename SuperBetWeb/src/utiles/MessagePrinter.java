package utiles;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagePrinter {

	// ////////////////////////////////////////////////////////////////////////////////////////////
	// Messaging functions
	// ////////////////////////////////////////////////////////////////////////////////////////////

	public static void showInformationMessage(String message) {
		if (message == null || "".equals(message.trim())) {
			message = "Error desconocido. Contacte al administrador";
		}
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public static void showWarningMessage(String message) {
		if (message == null || "".equals(message.trim())) {
			message = "Error desconocido. Contacte al administrador";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}

	public static void showErrorMessage(String message) {
		if (message == null || "".equals(message.trim())) {
			message = "Error desconocido. Contacte al administrador";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

}
