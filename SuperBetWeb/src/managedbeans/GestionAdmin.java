package managedbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import negocio.moderadores.interfaces.IModeradores;




@ManagedBean(name="gestionAdmin")
@SessionScoped
public class GestionAdmin {
	
	//TODO: DEJO SOLO UN EJEMPLO FALTA INCLUIR EL RESTO TAL CUAL ESTÁ EN EL DOCUMENTO
	// DE ARQUITECTURA, QUE LO HAGA OTRO,,,, TENGO SUEÑO.
	// FALTA INCLUIR LOS @EJB desde los manged bean hacia el negocio
	// FALTA MARCAR LOS SESSION BEANS DE NEGOCIO COMO EJBS
	//java:global[/<app-name>]/<module-name>/<beanname>[!<fully-qualified-interface-name>]
	@EJB(lookup="java:global/SuperBetNegocio/ControladorModeradores!negocio.moderadores.interfaces.IModeradores")
	private IModeradores moderadoresMGR;
	
	
}
