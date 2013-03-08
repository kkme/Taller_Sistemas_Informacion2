package persistencia.controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistencia.interfaces.ApuestaDAO;
import utiles.ExceptionManager;
import dominio.Apuesta;
import dominio.Combinacion;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;
import dominio.externo.ApuestaExterna;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApuestaDAOImpl implements ApuestaDAO {

	/*
	 * Queries
	 */
	private static final String BUSCAR_TIPOS_APUESTA = "select t from TipoApuesta t";

	@PersistenceContext(unitName = "SUPERBET_UNIT")
	private javax.persistence.EntityManager em;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<TipoApuesta> getTiposApuesta() {
		try {
			Query query = em.createQuery(BUSCAR_TIPOS_APUESTA);
			return query.getResultList();
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
	}

	@Override
	public void altaApuesta(Apuesta apuesta, Combinacion combinacion,
			List<Resultado> resultados, TipoApuesta tipoApuesta, Usuario user) {
		try {
			// refresco resultados
			ArrayList<Resultado> newResults = new ArrayList<Resultado>();
			for (Resultado r : resultados) {
				Resultado res = em.find(Resultado.class, r.getId());
				newResults.add(res);
			}
			// refresco tipo apuesta y usuario
			tipoApuesta = em.find(TipoApuesta.class, tipoApuesta.getId());
			user = em.find(Usuario.class, user.getId());

			apuesta.setResultados(newResults);
			apuesta.setTipoApuesta(tipoApuesta);
			apuesta.setUser(user);
			apuesta.setFuePaga(false);
			apuesta.setFechaApuesta(Calendar.getInstance());
			if (resultados.size() > 1) {
				combinacion = em.find(Combinacion.class, combinacion.getId());
				apuesta.setCombinacion(combinacion);
			}

			em.merge(apuesta);

		} catch (Throwable ex) {
			ex.printStackTrace();
			throw ExceptionManager.process(ex);
		}
	}

	@Override
	public List<TipoEvento> getEventosExternos() {
	/*	IntegracionwsService service1 = new IntegracionwsService();
		System.out.println("Create Web Service...");
		// Integracionws port2 = service1.getIntegracionws();
		// DatosEventoArray x = port2.obtenerEventos();

		List<DatosEvento> dataEventos;// = x.getItem();
		// TODO: quitar esto cuando se pueda consumir el ws
		dataEventos = new ArrayList<DatosEvento>();
		DatosEvento des = new DatosEvento();
		XMLGregorianCalendar calen = new XMLGregorianCalendarImpl();
		calen.setDay(11);
		calen.setMonth(10);
		calen.setYear(11);
		des.setFecha(calen);
		des.setId(16584);
		des.setLatitud(new BigDecimal(-56.1657));
		des.setLongitud(new BigDecimal(34.52698));
		des.setNombre("Clasico Nacional Pe�arol");
		dataEventos.add(des);
		
		// ***********************FIN STUB***********************

		List<TipoEvento> resultado = new ArrayList<TipoEvento>();
		for (DatosEvento de : dataEventos) {
			TipoEvento te = new TipoEvento();
			Evento evento = new Evento();
			GregorianCalendar fecha = new GregorianCalendar(de.getFecha()
					.getYear(), de.getFecha().getMonth(), de.getFecha()
					.getDay());
			evento.setInicio(fecha);
			evento.setFin(fecha);
			evento.setNombre(de.getNombre());
			evento.setUbicacion(new Ubicacion());
			evento.getUbicacion().setLatitud(
					Double.valueOf(de.getLatitud().toString()));
			evento.getUbicacion().setLongitud(
					Double.valueOf(de.getLongitud().toString()));
			te.setEvento(evento);
			te.setResultados(new ArrayList<Resultado>());
			tmp_agregarResultadosDummy(te);
			resultado.add(te);
		}

		return resultado;*/
		return null;
	}

	private void tmp_agregarResultadosDummy(TipoEvento te) {
		System.out.println("ingrese resultados dummy");
		for(int i=1;i<5;i++){
			Resultado r = new Resultado();
			r.setCuota(1.25);
			r.setDescription("Resultado "+i+" "+te.getEvento().getNombre());
			te.getResultados().add(r);
		}
		
	}

	@Override
	public void altaApuestaCombinada(Usuario usuario, Combinacion combinacion,
			List<Resultado> resultados, Apuesta apuesta) {

		TipoApuesta tipoApuesta = (TipoApuesta) em
				.createQuery("from TipoApuesta where name like 'Combinada' ")
				.getResultList().get(0);
		
		
			try {
				// refresco resultados
				ArrayList<Resultado> newResults = new ArrayList<Resultado>();
				for (Resultado r : resultados) {
					Resultado res = em.find(Resultado.class, r.getId());
					newResults.add(res);
				}
				// refresco tipo apuesta y usuario
				tipoApuesta = em.find(TipoApuesta.class, tipoApuesta.getId());
				usuario = em.find(Usuario.class, usuario.getId());

				apuesta.setResultados(newResults);
				apuesta.setTipoApuesta(tipoApuesta);
				apuesta.setUser(usuario);
				apuesta.setFuePaga(false);
				apuesta.setFechaApuesta(Calendar.getInstance());
				if (resultados.size() > 1) {
					combinacion = em.find(Combinacion.class, combinacion.getId());
					apuesta.setCombinacion(combinacion);
				}

				em.merge(apuesta);

			} catch (Throwable ex) {
				ex.printStackTrace();
				throw ExceptionManager.process(ex);
			}

	}

	@Override
	public int altaApuestaMobile(Double monto, Integer idResultado,
			int resultadoExacto, Integer idUser) {
		int resultado =-1;
		try {
			// refresco resultados
			ArrayList<Resultado> newResults = new ArrayList<Resultado>();
			
			Resultado res = em.find(Resultado.class, idResultado);
			newResults.add(res);
		
			// refresco tipo apuesta y usuario
			TipoApuesta tipoApuesta = em.find(TipoApuesta.class, resultadoExacto);
			Usuario user = em.find(Usuario.class, idUser);
			user.setMonto(user.getMonto()-monto);
			Apuesta apuesta = new Apuesta();
			apuesta.setAmount(monto);
			apuesta.setResultados(newResults);
			apuesta.setTipoApuesta(tipoApuesta);
			apuesta.setUser(user);
			apuesta.setFuePaga(false);
			apuesta.setFechaApuesta(Calendar.getInstance());
			apuesta.setCombinacion(null);
			em.merge(user);
			Apuesta a = em.merge(apuesta);
			resultado = a.getId();
			return resultado;
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw ExceptionManager.process(ex);
		}
		
	
	}
	

	@Override
	public void borrarApuestaExterna(Integer idApuestaExterna){
		//lo encuentro por idApuesta
		Query q = em.createQuery("from ApuestaExterna where idApuestaExterna = :idApuesta");
		q.setParameter("idApuesta", idApuestaExterna);
		ApuestaExterna ae = (ApuestaExterna) q.getResultList().get(0);
		em.remove(ae);
	}

	@Override
	public void registrarApuestaExterna(Integer idUsuario,
			Integer idApuestaExterna, Double monto, Double gnancias) {
		
		Usuario u = em.find(Usuario.class, idUsuario);
		ApuestaExterna ae = new ApuestaExterna();
		ae.setGananciaEsperada(gnancias);
		ae.setIdApuestaExterna(idApuestaExterna);
		ae.setMontoApostado(monto);
		ae.setUsuario(u);
		
		em.persist(ae);

		
	}

	@Override
	public List<Apuesta> obtenerApuestasPagasUsuario(Integer idusuario) {
		Query query = em.createQuery("FROM Apuesta a" +
				" WHERE a.user.id = "+idusuario.toString()+
				" AND a.fuePaga = true ");
		query.getResultList();
		return query.getResultList();
	}
}
