package negocio.moderadores.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;

import persistencia.interfaces.EventoDAO;
import persistencia.interfaces.PersistenciaDAO;
import persistencia.interfaces.UsuarioDAO;
import negocio.moderadores.interfaces.IModeradores;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ControladorModeradores implements IModeradores {
	
	//java:global[/<app-name>]/<module-name>/<beanname>[!<fully-qualified-interface-name>]
	@EJB(lookup="java:global/SuperBetPersistencia/EventoDAOImpl!persistencia.interfaces.EventoDAO")
	private EventoDAO eventoDAO;
	
	
	@Override
	public List<TipoApuesta> listaTiposApuestasNoPagosDeEvento(
			Evento evento) {
		return eventoDAO.listaTiposApuestasNoPagosDeEvento(evento);
	}

	@Override
	public List<Resultado> listarResultados(
			TipoApuesta tipoApuesta, Evento evento) {
		
		return eventoDAO.listarResultados(tipoApuesta, evento);
	}

	@Override
	public void pagarApuestas(Evento event,
			TipoApuesta tipoApuesta,
			Resultado result) {
		eventoDAO.pagarApuestas(event,tipoApuesta,result);
		
	}

}
