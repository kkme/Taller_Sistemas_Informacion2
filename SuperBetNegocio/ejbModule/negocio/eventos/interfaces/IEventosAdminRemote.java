package negocio.eventos.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import dominio.Combinacion;
import dominio.Competicion;
import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;
import dominio.Ubicacion;
@Remote
public interface IEventosAdminRemote {

	public Evento insertarEvento(Evento evento);

	public void insertarTipoEvento(TipoEvento tipoEvento);

	public ArrayList<Evento> obtenerTodosEventos();

	public Competicion obtenerCompeticionDeEvento(Integer idEvento);

	public ArrayList<TipoApuesta> obtenerTiposApuestaEvento(Integer idEvento);

	public void insertarNuevosResultados(Evento evento , Integer ta, ArrayList<Resultado> resultados, Date inicioPerApuesta, Date finPerApuesta);

	public ArrayList<Evento> obtenerUltimosNEventos(int numUltEventos);

	public List<Resultado> obtenerResultados(TipoEvento te);

	public List<TipoEvento> obtenerTipoEventos_Evento(Integer id);

	public ArrayList<Resultado> getResultados(Integer id, Integer idTipoApuesta);

	public TipoEvento getTipoEvento(Integer idEvento, Integer idTipoApuesta_aux);

	public List<TipoEvento> tipoEventosNoPagos();

	public Evento obtenerEvento(String nombre);

	public void agregarCombinada(List<TipoEvento> comb_combinacion,String nombre, String desc);

	public List<Combinacion> getCombinacionesNoPagas();

	public void pagarApuestaCombinada(Combinacion pc_selectedComb);

	public List<TipoEvento> getResultadosEventosRecientes(Usuario u);

	public Ubicacion obtenerUbicacionDeEvento(Integer id);

	public void actualizarEvento(Evento eventoSelected);

	public void pagarEventoExterno(int idUsuario, int idApuesta, Double ganancia) throws Exception;

	public List<Evento> obtenerEventosImportantes(int maxNumVideos);

}
