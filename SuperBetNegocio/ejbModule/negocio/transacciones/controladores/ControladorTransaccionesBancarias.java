package negocio.transacciones.controladores;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import persistencia.interfaces.PersistenciaDAO;
import negocio.transacciones.interfaces.IPagos;
import negocio.transacciones.interfaces.ITransaccion;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ControladorTransaccionesBancarias implements ITransaccion,IPagos{
	
	//java:global[/<app-name>]/<module-name>/<beanname>[!<fully-qualified-interface-name>]
	@EJB(lookup="java:global/SuperBetPersistencia/PersistenciaDAOImpl!persistencia.interfaces.PersistenciaDAO")
	private PersistenciaDAO persistenciaDAO;

}
