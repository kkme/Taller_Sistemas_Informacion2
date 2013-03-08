package persistencia.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.Query;

import persistencia.interfaces.EventoDAO;
import utiles.ExceptionManager;
import dominio.Apuesta;
import dominio.Combinacion;
import dominio.Competicion;
import dominio.Deporte;
import dominio.Evento;
import dominio.IdTipoEvento;
import dominio.Pais;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Ubicacion;
import dominio.Usuario;
import dominio.externo.ApuestaExterna;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EventoDAOImpl implements EventoDAO {

	private static final String OBTENER_TIPOSAPUESTA_POR_NOMBRE = "select ta from TipoApuesta ta where ta.name = :taName";
	private static final String OBTENER_TODOS_EVENTOS = "select e from Evento e";
	private static final String BUSCAR_COMP_DE_EVENTO = "select e.competicion from Evento e where e.id = :idEvento";
	private static final String BUSCAR_DEPORTE_COMP = "select c.deporte from Competicion c where c.id = :idComp";
	private static final String OBTENER_ULTIMOS_N_EVENTOS = "select e from Evento e order by e.inicio desc limit :numEventos";
	private static final String OBTENER_RESULTADOS = "select te.resultados from TipoEvento te where te.tipoApuesta.id = :idTipoApuesta and te.evento.id = :idEvento";
	private static final String OBTENER_TIPOS_EVENTO_DE_EVENTO = "select e.tiposEvento from Evento e where e.id = :idEvento";
	private static final String OBTENER_TIPO_EVENTO = "select te from TipoEvento te where te.evento.id=:idEvento and te.tipoApuesta.id=:idTipoApuesta";
	private static final String OBTENER_TODOS_TIPO_EVENTOS_NO_PAGOS = "from TipoEvento te where te.fuePagada is null or te.fuePagada = false";
	private static final String BUSCAR_UBICACION_DE_EVENTO = "select e.ubicacion from Evento e where e.id = :idEvento";
	private static final String BUSCAR_EVENTOS_MAS_IMPORTANTES = "select e from Evento e order by e.importancia desc";

	@javax.persistence.PersistenceContext(unitName = "SUPERBET_UNIT")
	private javax.persistence.EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Evento insertarEvento(Evento evento) {
		try {
			Ubicacion ubicacion = evento.getUbicacion();
			ubicacion = em.merge(ubicacion);
			evento.setUbicacion(ubicacion);
			evento = em.merge(evento);
			return evento;
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertarTipoEvento(TipoEvento tipoEvento) {
		try {
			em.merge(tipoEvento);
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Evento> obtenerTodosEventos() {
		Query q = em.createQuery(OBTENER_TODOS_EVENTOS);
		return (ArrayList<Evento>) q.getResultList();
	}

	@Override
	public Competicion obtenerCompeticionDeEvento(Integer idEvento) {
		Competicion comp = null;
		try {
			Query q = em.createQuery(BUSCAR_COMP_DE_EVENTO);
			q.setParameter("idEvento", idEvento);
			comp = (Competicion) q.getResultList().get(0);

			Query q1 = em.createQuery(BUSCAR_DEPORTE_COMP);
			q1.setParameter("idComp", comp.getId());
			Deporte dep = (Deporte) q1.getResultList().get(0);
			// comp.setDeporte(dep);
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return comp;
	}

	@Override
	public ArrayList<TipoApuesta> obtenerTipoApuestasEvento(Integer idEvento) {
		ArrayList<TipoApuesta> tiposApuesta = null;
		try {
			Query q = em
					.createQuery("select te.tipoApuesta from TipoEvento te join te.tipoApuesta where te.evento.id=:idEvento");
			q.setParameter("idEvento", idEvento);
			tiposApuesta = (ArrayList<TipoApuesta>) q.getResultList();
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return tiposApuesta;

	}

	@Override
	public void insertarNuevosResultados(Evento e, Integer idta,
			ArrayList<Resultado> resultados, Date inicioPerApuesta,
			Date finPerApuesta) {
		try {

			// Find del evento
			e = em.find(Evento.class, e.getId());
			// Find del tipo de apuesta
			TipoApuesta nta = em.find(TipoApuesta.class, idta);

			TipoEvento te = new TipoEvento();

			te.setEvento(e);
			te.setTipoApuesta(nta);
			te.setInicioPerApuesta(inicioPerApuesta);
			te.setFinPerApuesta(finPerApuesta);
			te.setResultados(resultados);
			em.merge(te);

		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
	}

	@Override
	public ArrayList<Evento> obtenerUltimosNEventos(int numUltEventos) {
		ArrayList<Evento> eventos = null;
		try {
			Query q = em.createQuery(OBTENER_ULTIMOS_N_EVENTOS);
			q.setParameter("numEventos", numUltEventos);
			eventos = (ArrayList<Evento>) q.getResultList();
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return eventos;
	}

	@Override
	public List<Resultado> obtenerResultados(TipoEvento te) {
		ArrayList<Resultado> resultados = null;
		try {
			Query q = em.createQuery(OBTENER_RESULTADOS);
			q.setParameter("idEvento", te.getEvento().getId());
			q.setParameter("idTipoApuesta", te.getTipoApuesta().getId());
			resultados = (ArrayList<Resultado>) q.getResultList();
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return resultados;
	}

	@Override
	public List<TipoApuesta> listaTiposApuestasNoPagosDeEvento(Evento evento) {
		try {
			Query query = em.createQuery("from TipoEvento te "
					+ "where te.evento.id = :idevento");
			query.setParameter("idevento", evento.getId());
			List<TipoApuesta> resultado = new ArrayList<TipoApuesta>();

			for (TipoEvento aux : (List<TipoEvento>) query.getResultList()) {
				if (aux.getFuePagada() == null || !aux.getFuePagada()) {// devuelvo
																		// solo
																		// los
																		// no
																		// pagos
					resultado.add(aux.getTipoApuesta());
				}
			}
			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Resultado> listarResultados(TipoApuesta tipoApuesta,
			Evento evento) {
		Query query = em.createQuery("select res " + "from TipoEvento te "
				+ " join te.resultados res "
				+ " where te.evento.id =:idevento and "
				+ " te.tipoApuesta.id = :idtipoapuesta");
		query.setParameter("idevento", evento.getId());
		query.setParameter("idtipoapuesta", tipoApuesta.getId());

		List<Resultado> resultado = (List<Resultado>) query.getResultList();

		return resultado;
	}

	@Override
	public void pagarApuestas(Evento event, TipoApuesta tipoApuesta,
			Resultado result) {
		// marco el resultado como ganador dentro de su grupo
		Query q1 = em
				.createQuery("select te from TipoEvento te where te.evento.id = :idevento and te.tipoApuesta.id = :idapuesta");

		q1.setParameter("idevento", event.getId());
		q1.setParameter("idapuesta", tipoApuesta.getId());

		TipoEvento te = (TipoEvento) q1.getSingleResult();

			te.setGanador(result);
			te.setFuePagada(true);
			em.merge(te);
			// em.flush(); este flush no deber�a estar

			// encuentro todas las apuestas para el resultado seleccionado
			Query q2 = em.createQuery("select ra "
					+ " from Resultado r join r.apuestas as ra"
					+ " where r.id=:rid ");

			q2.setParameter("rid", result.getId());

			List<Apuesta> apus = (List<Apuesta>) q2.getResultList();

			Double paga = result.getCuota();

			int i = 0;
			for (Apuesta a : apus) {
				// no pago combinadas, ni apuestas previamente pagas
				if (!a.getTipoApuesta().getName().equalsIgnoreCase("Combinada")
						&& a.getFuePaga() != null && !a.getFuePaga()) {
					Usuario u = a.getUser();
					Double ganancia = paga * a.getAmount();
					u.setMonto(u.getMonto() + ganancia);
					a.setFuePaga(true);
					a.setGanancia(ganancia);
					em.merge(u);
					em.merge(a);
					i++;
				}
			}
			em.flush();

			System.out.println("Se realizo el pago de " + i + " apuestas");

			// para cada apuesta
			// consigo la lista de usuarios = resultado.apuesta.usuario

			// marco el la pareja tipoevento-evento como paga
		
	}

	@Override
	public List<TipoEvento> obtenerTipoEventos_Evento(Integer idEvento) {
		Query query = em.createQuery("from TipoEvento te "
				+ "where te.evento.id = :idevento");
		query.setParameter("idevento", idEvento);
		return query.getResultList();
	}

	@Override
	public ArrayList<Resultado> getResultadosEventoTipoApuesta(
			Integer idevento, Integer idTipoApuesta) {

		System.out.println("busco Resulgados de evento " + idevento
				+ " tipo apuesta " + idTipoApuesta);
		ArrayList<Resultado> resultados = null;
		try {
			Query q = em.createQuery(OBTENER_RESULTADOS);
			q.setParameter("idEvento", idevento);
			q.setParameter("idTipoApuesta", idTipoApuesta);
			resultados = (ArrayList<Resultado>) q.getResultList();
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return resultados;
	}

	@Override
	public TipoEvento getTipoEvento(Integer idEvento, Integer idTipoApuesta) {
		System.out.println("busco TipoEvento con evento " + idEvento
				+ " y tipo apuesta " + idTipoApuesta);
		TipoEvento resultado = null;
		try {
			Query q = em.createQuery(OBTENER_TIPO_EVENTO);
			q.setParameter("idEvento", idEvento);
			q.setParameter("idTipoApuesta", idTipoApuesta);
			List<TipoEvento> resultados = q.getResultList();
			if ((resultados != null) && (!resultados.isEmpty())) {
				resultado = (TipoEvento) q.getResultList().get(0);
			}
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return resultado;
	}

	@Override
	public List<TipoEvento> getTipoEventosNoPagos() {
		Query q = em.createQuery(OBTENER_TODOS_TIPO_EVENTOS_NO_PAGOS);
		return (List<TipoEvento>) q.getResultList();
	}

	@Override
	public Evento obtenerEvento(String nombre) {
		Evento evento = null;
		try {
			evento = em.find(Evento.class, nombre);
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return evento;
	}

	@Override
	public void agregarCombinada(List<TipoEvento> combinacion, String nombre,
			String desc) {

		Combinacion c = new Combinacion();
		c.setDescripcion(desc);
		c.setNombre(nombre);
		c.setTipo_eventos(null);

		em.persist(c);

		for (TipoEvento t : combinacion) {
			Evento e = em.merge(t.getEvento());
			TipoApuesta ta = em.merge(t.getTipoApuesta());
			t.setEvento(e);
			t.setTipoApuesta(ta);
			TipoEvento aux = em.merge(t);
			aux.setCombinacion(c);
			em.merge(aux);
		}

		em.flush();
	}

	/**
	 * Devuelve las combinaciones no pagas que se pueden pagar esto es, que
	 * todos sus Eventos involucrados hayan sido pagos
	 */
	@Override
	public List<Combinacion> getCombinacionesNoPagas() {
		Query q = em
				.createQuery("select c from Combinacion c where c.fuePagada is null or c.fuePagada=false");
		List<Combinacion> noPagas = q.getResultList();

		List<Combinacion> resultado = new ArrayList<Combinacion>();

		for (Combinacion c : noPagas) {
			if (sePuedePagar(c)) {
				resultado.add(c);
			}
		}
		return resultado;
	}

	@Override
	public Ubicacion obtenerUbicacionDeEvento(Integer id) {
		Ubicacion u;
		try {
			Query q = em.createQuery(BUSCAR_UBICACION_DE_EVENTO);
			q.setParameter("idEvento", id);
			u = (Ubicacion) q.getResultList().get(0);
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return u;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarEvento(Evento eventoSelected) {
		Evento e = em.merge(eventoSelected);
		Competicion c = em.find(Competicion.class, eventoSelected
				.getCompeticion().getId());
		e.setCompeticion(c);
		Ubicacion u = em.find(Ubicacion.class, eventoSelected.getUbicacion()
				.getId());
		e.setUbicacion(u);
		e.setFin(eventoSelected.getFin());
		e.setImportancia(eventoSelected.getImportancia());
		e.setInicio(eventoSelected.getInicio());
		e.setNombre(eventoSelected.getNombre());
		// em.merge(e);
	}

	/**
	 * 
	 * @param combinacion
	 * @return true si todas los tipoEventos que lo componen ya fueron pagos si
	 *         getFuePagada retorna null es lo mismo que si fuese false
	 */
	private boolean sePuedePagar(Combinacion c) {
		try {
			boolean sePuedePagar = true;
			List<TipoEvento> lista = c.getTipo_eventos();
			if (lista.isEmpty())
				return false;

			for (TipoEvento te : lista) {
				if (te.getFuePagada() != null) {
					sePuedePagar = sePuedePagar && te.getFuePagada();
				} else {
					sePuedePagar = false;
					break;
				}
			}
			return sePuedePagar;
		} catch (NullPointerException e) {
			System.out
					.println("Salto Null Pointer en EventoDAOImpl::sePuedePagar");
			return false;
		}
	}

	@AroundInvoke
	Object interceptorGeneral(InvocationContext ic) throws Exception {
		ic.getTarget().toString();

		ic.getMethod().getName();

		try {
			if ("pagarApuestaCombinada".equalsIgnoreCase(ic.getMethod()
					.getName())) {
				Combinacion c = null;
				for (Object o : ic.getParameters()) {

					if (o instanceof Combinacion) {
						c = (Combinacion) o;
					}
				}
				if (c == null)
					throw new Exception("No se tiene combinacion");

				if (!sePuedePagar(c)) {
					throw new Exception("Aún no se puede pagar Apuesta");
				}
			}

			if ("agregarCombinada".equalsIgnoreCase(ic.getMethod().getName())) {
				// No puede haber previamente una combinada que incluya a esta
				// otra
				List<TipoEvento> comb = null;
				for (Object o : ic.getParameters()) {
					if (o instanceof ArrayList<?>) {
						comb = (ArrayList<TipoEvento>) o;
						break;
					}
				}
				if (obtenerCombinacionTiposEvento(comb) != null)
					throw new Exception(
							"Ya existe una combinacion con esos tipos de eventos");

			}

			return ic.proceed();
		} finally {
			System.out.println("Intercepte: " + ic.getTarget().getClass()
					+ "  " + ic.getMethod().getName());
		}

	}

	@Override
	public void pagarApuestaCombinada(Combinacion comb) {

		try {
			// Busco las apuestas de la combinación a pagar
			Query q = em
					.createQuery(" from Apuesta a where a.combinacion is not null and a.combinacion.id =:idcomb ");
			q.setParameter("idcomb", comb.getId());

			List<Apuesta> apuestas = (List<Apuesta>) q.getResultList();

			List<TipoEvento> teventos = comb.getTipo_eventos();
			// busco los resultados que salieron ganadores de cada tipoEvento
			List<Resultado> ganadores = new ArrayList<Resultado>();
			for (TipoEvento te : teventos) {
				ganadores.add(te.getGanador());
			}

			// calculo apuestas que
			List<Apuesta> apuestasGanadoras = new ArrayList<Apuesta>();
			for (Apuesta a : apuestas) {
				boolean gano = true;
				for (Resultado r : a.getResultados()) {// recorro resultados
														// apostados
					gano = gano && ganadores.contains(r);
				}

				if (gano) {
					apuestasGanadoras.add(a);
				}
			}

			// formula es : MontoApostado * montoResultado1 * ... *
			// montoResultadoN
			for (Apuesta a : apuestasGanadoras) {
				double premio = a.getAmount();
				for (Resultado r : a.getResultados()) {
					premio *= r.getCuota();
				}

				Usuario u = a.getUser();
				u.setMonto(u.getMonto() + premio);
				Apuesta apu = em.find(Apuesta.class, a.getId());
				apu.setFuePaga(true);
				apu.setGanancia(premio);
				em.merge(apu);
				em.merge(u);

			}
			Combinacion c = em.find(Combinacion.class, comb.getId());
			c.setFuePagada(true);
			em.merge(c);

			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoEvento> getResultadosEventosRecientes(Usuario u) {

		/**
		 * TODO: hay que seguir trabajando la consulta para que retorno apuestas
		 * ya pagas en los �ltimos 30
		 */
		Query q = em
				.createQuery(" select te from Usuario u join u.apuestas ua, TipoEvento te "
						+ " join te.tipoApuesta.apuestas a "
						+ " where ua.id = a.id "
						+ " and te.fuePagada = true "
						+ " and te.ganador is not null" + " and u.id = :iduser");
		q.setParameter("iduser", u.getId());

		List<TipoEvento> lista = q.getResultList();
		System.out.println(lista.size());
		return lista;
	}

	/**
	 * lo que quiero son los tipoeventos de una competicion particular en un
	 * pais particular cuya tipo de apuesta es tmb una particular eso mismo :D
	 * osea mira la pantalla de apuestas y te das cuenta lo que quiero filtras
	 * por tipo de apuesta, por pais y por competencia dentro del pais
	 * 
	 * que es esto booo!! un chat de comentarios!! mira que aca queda todo
	 * grabado!! el svn sabe bien quien comitea !!
	 * 
	 * si, si, es un chat de comentarios!!
	 */

	public List<TipoEvento> obtenerTipoEventosCondicion(String tipoApuesta,
			String deporte, String pais, String competencia) {
		Query query = null;
		if (competencia.equals("") && pais.equals("")) {
			query = em
					.createQuery(" select distinct te from TipoEvento te join fetch te.resultados"
							+ " where te.tipoApuesta.name like :tipoapuesta"
							+ " and te.evento.competicion.deporte.name like :sport ");
			query.setParameter("tipoapuesta", tipoApuesta);
			query.setParameter("sport", deporte);
		} else if (competencia.equals("")) {
			query = em
					.createQuery(" select distinct te from TipoEvento te join fetch te.resultados"
							+ " where te.evento.competicion.country.name like :namepais"
							+ " and te.evento.competicion.deporte.name like :sport "
							+ " and te.tipoApuesta.name like :tipoapuesta");
			query.setParameter("tipoapuesta", tipoApuesta);
			query.setParameter("sport", deporte);
			query.setParameter("namepais", pais);
		} else {
			query = em
					.createQuery(" select distinct te from TipoEvento te join fetch te.resultados"
							+ " where te.evento.competicion.nombre like :namecomp "
							+ " and te.evento.competicion.country.name like :namepais "
							+ " and te.evento.competicion.deporte.name like :sport "
							+ " and te.tipoApuesta.name like :tipoapuesta");
			query.setParameter("tipoapuesta", tipoApuesta);
			query.setParameter("sport", deporte);
			query.setParameter("namecomp", competencia);
			query.setParameter("namepais", pais);
		}

		List<TipoEvento> resultado = new ArrayList<TipoEvento>();

		// filtro las apuestas que aún no se pagaron
		for (TipoEvento te : (List<TipoEvento>) query.getResultList()) {
			if (te.getFuePagada() == null || !te.getFuePagada()) {
				resultado.add(te);
			}
		}

		return resultado;
	}

	/**
	 * @return combinaci�n existente que contenga los tipoEvento pasados por
	 *         par�metro
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Combinacion obtenerCombinacionTiposEvento(
			Collection<TipoEvento> tiposEvento) {
		Query query = em
				.createQuery(" select DISTINCT c from Combinacion c join fetch c.tipo_eventos");

		for (Combinacion combinacion : (List<Combinacion>) query
				.getResultList()) {
			int cantEncontre = 0;
			for (TipoEvento tipoEvento : tiposEvento) {
				boolean encontre = false;
				for (TipoEvento tipoEvento2 : combinacion.getTipo_eventos()) {
					encontre = tipoEvento2.getEvento().getId()
							.equals(tipoEvento.getEvento().getId());
					if (encontre) {
						break;
					}
				}
				if (!encontre) {
					break;
				}
				cantEncontre++;
			}
			if (cantEncontre == tiposEvento.size()) {
				return combinacion;
			}
		}
		return null;
	}

	@Override
	public void pagarEventoExterno(int idUsuario, int idApuesta, Double ganancia)
			throws Exception {
		Usuario u = em.find(Usuario.class, idUsuario);
		ApuestaExterna ae = em.find(ApuestaExterna.class, idApuesta);
		if (ganancia != ae.getGananciaEsperada())
			throw new Exception("Ganancia Incorrecta");
		u.setMonto(u.getMonto() + ganancia);
		em.merge(u);
		em.remove(ae);
		em.flush();
	}

	@Override
	public List<Evento> obtenerEventosImportantes(int maxNumVideos) {
		List<Evento> ret = null;
		try {
			Query q = em.createQuery(BUSCAR_EVENTOS_MAS_IMPORTANTES);
			q.setMaxResults(maxNumVideos);
			ret = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<TipoEvento> getResultadosEventosRecientes() {

		Query q = em.createQuery(" select te from TipoEvento te "
				+ " where te.fuePagada = true "
				+ " and te.ganador is not null order by te.evento.fin");

		List<TipoEvento> lista = (List<TipoEvento>) q.getResultList();
		return lista;
	}

	@Override
	public List<TipoEvento> getResultadosEvento(int evento) {

		Query q = em
				.createQuery(" select te from TipoEvento te "
						+ " where te.fuePagada = true "
						+ " and te.ganador is not null and te.evento.id = :idEvento order by te.evento.fin");
		q.setParameter("idEvento", evento);
		List<TipoEvento> lista = (List<TipoEvento>) q.getResultList();
		return lista;
	}

}
