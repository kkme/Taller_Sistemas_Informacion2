package persistencia.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import dominio.Combinacion;
import dominio.Competicion;
import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;
import dominio.Ubicacion;

@Local
public interface EventoDAO {

	public Evento insertarEvento(Evento evento);

	public void insertarTipoEvento(TipoEvento tipoEvento);

	public ArrayList<Evento> obtenerTodosEventos();
	
	public Competicion obtenerCompeticionDeEvento(Integer idEvento);

	public ArrayList<TipoApuesta> obtenerTipoApuestasEvento(Integer idEvento);

	public void insertarNuevosResultados(Evento e, Integer ta,
			ArrayList<Resultado> resultados, Date inicioPerApuesta,
			Date finPerApuesta);

	public List<TipoApuesta> listaTiposApuestasNoPagosDeEvento(Evento evento);

	public List<Resultado> listarResultados(TipoApuesta tipoApuesta,
			Evento evento);

	public ArrayList<Evento> obtenerUltimosNEventos(int numUltEventos);

	public List<Resultado> obtenerResultados(TipoEvento te);

	public void pagarApuestas(Evento event, TipoApuesta tipoApuesta,
			Resultado result);

	public List<TipoEvento> obtenerTipoEventos_Evento(Integer idEvento);

	public ArrayList<Resultado> getResultadosEventoTipoApuesta(
			Integer idevento, Integer idTipoApuesta);

	public TipoEvento getTipoEvento(Integer idEvento, Integer idTipoApuesta);

	public List<TipoEvento> getTipoEventosNoPagos();

	public Evento obtenerEvento(String nombre);

	public void agregarCombinada(List<TipoEvento> combinacion, String nombre,
			String desc);

	public Ubicacion obtenerUbicacionDeEvento(Integer id);

	public List<Combinacion> getCombinacionesNoPagas();

	public void actualizarEvento(Evento eventoSelected);

	public void pagarApuestaCombinada(Combinacion comb);

	public List<TipoEvento> obtenerTipoEventosCondicion(String tipoApuesta, String deporte,
			String pais, String competencia);

	public Combinacion obtenerCombinacionTiposEvento(Collection<TipoEvento> tiposEvento);
	
	public List<TipoEvento> getResultadosEventosRecientes(Usuario u);

	public void pagarEventoExterno(int idUsuario, int idApuesta, Double ganancia) throws Exception;

	public List<Evento> obtenerEventosImportantes(int maxNumVideos);

	public List<TipoEvento> getResultadosEventosRecientes();

	List<TipoEvento> getResultadosEvento(int evento);

}
