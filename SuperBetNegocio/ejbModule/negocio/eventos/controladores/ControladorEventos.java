package negocio.eventos.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import negocio.eventos.interfaces.IEventosAdmin;
import negocio.eventos.interfaces.IEventosAdminRemote;
import negocio.eventos.interfaces.IEventosModerador;
import negocio.eventos.interfaces.IEventosUsuario;
import persistencia.interfaces.EventoDAO;
import dominio.Combinacion;
import dominio.Competicion;
import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;
import dominio.Ubicacion;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ControladorEventos implements IEventosAdmin,IEventosAdminRemote, IEventosModerador, IEventosUsuario, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//java:global[/<app-name>]/<module-name>/<beanname>[!<fully-qualified-interface-name>]
	@EJB(lookup="java:global/SuperBetPersistencia/EventoDAOImpl!persistencia.interfaces.EventoDAO")
	private EventoDAO eventoDAO;

	@Override
	public void insertarTipoEvento(TipoEvento tipoEvento) {
		eventoDAO.insertarTipoEvento(tipoEvento);
	}

	@Override
	public Evento insertarEvento(Evento evento) {
		return eventoDAO.insertarEvento(evento);
	}

	@Override
	public ArrayList<Evento> obtenerTodosEventos() {
		return eventoDAO.obtenerTodosEventos();
	}

	@Override
	public Competicion obtenerCompeticionDeEvento(Integer idEvento) {
		return eventoDAO.obtenerCompeticionDeEvento(idEvento);
	}


	@Override
	public ArrayList<TipoApuesta> obtenerTiposApuestaEvento(Integer idEvento) {
		return eventoDAO.obtenerTipoApuestasEvento(idEvento);
	}

	@Override
	public void insertarNuevosResultados(Evento e,Integer idta, ArrayList<Resultado> resultados, Date inicioPerApuesta, Date finPerApuesta) {
		eventoDAO.insertarNuevosResultados(e,idta,resultados,  inicioPerApuesta,  finPerApuesta); 
	}

	@Override
	public ArrayList<Evento> obtenerUltimosNEventos(int numUltEventos) {
		return eventoDAO.obtenerUltimosNEventos(numUltEventos);
	}

	@Override
	public List<Resultado> obtenerResultados(TipoEvento te) {
		return eventoDAO.obtenerResultados(te);
	}

	@Override
	public List<TipoEvento> obtenerTipoEventos_Evento(Integer idEvento) {
		return eventoDAO.obtenerTipoEventos_Evento(idEvento);
	}

	@Override
	public ArrayList<Resultado> getResultados(Integer idevento, Integer idTipoApuesta) {
		return eventoDAO.getResultadosEventoTipoApuesta(idevento,idTipoApuesta);
	}

	@Override
	public TipoEvento getTipoEvento(Integer idEvento, Integer idTipoApuesta) {
		return eventoDAO.getTipoEvento(idEvento,idTipoApuesta);
	}

	@Override
	public List<TipoEvento> tipoEventosNoPagos() {
		
		return eventoDAO.getTipoEventosNoPagos();
	}

	@Override
	public Evento obtenerEvento(String nombre) {
		return eventoDAO.obtenerEvento(nombre);
	}

	@Override
	public void agregarCombinada(List<TipoEvento> combinacion,String nombre, String desc) {
		eventoDAO.agregarCombinada(combinacion,nombre,desc);
		
	}

	@Override
	public List<Combinacion> getCombinacionesNoPagas() {
		return eventoDAO.getCombinacionesNoPagas();
	}

	@Override
	public void pagarApuestaCombinada(Combinacion comb) {
		 eventoDAO.pagarApuestaCombinada(comb);
		
	}

	@Override
	public List<TipoEvento> getResultadosEventosRecientes(Usuario u) {
		return eventoDAO.getResultadosEventosRecientes(u);
	}
	
	

	@Override
	public Ubicacion obtenerUbicacionDeEvento(Integer id) {
		return eventoDAO.obtenerUbicacionDeEvento(id);
	}

	@Override
	public void actualizarEvento(Evento eventoSelected) {
		eventoDAO.actualizarEvento(eventoSelected);
	}

	@Override
	public List<TipoEvento> obtenerTipoEventosCondicion(String tipoApuesta, String deporte,
			String pais, String competencia) {
		return eventoDAO.obtenerTipoEventosCondicion(tipoApuesta, deporte,
				pais, competencia);
		
	}

	@Override
	public Combinacion obtenerCombinacionTiposEvento(
			Collection<TipoEvento> tiposEvento) {
		return eventoDAO.obtenerCombinacionTiposEvento(tiposEvento);
	}

	@Override
	public void pagarEventoExterno(int idUsuario, int idApuesta, Double ganancia) throws Exception {
		
		eventoDAO.pagarEventoExterno(idUsuario,idApuesta,ganancia);
	}

	@Override
	public List<Evento> obtenerEventosImportantes(int maxNumVideos) {
		return eventoDAO.obtenerEventosImportantes(maxNumVideos);
	}

	@Override
	public List<TipoEvento> getResultadosEventosRecientes() {
		return eventoDAO.getResultadosEventosRecientes();
		
	}

	@Override
	public List<TipoEvento> getResultadosEvento(Integer evento) {
		return eventoDAO.getResultadosEvento(evento);
	}

}
