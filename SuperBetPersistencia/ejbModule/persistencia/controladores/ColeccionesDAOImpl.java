package persistencia.controladores;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import persistencia.interfaces.ColeccionesDAO;
import utiles.ExceptionManager;
import dominio.Competicion;
import dominio.Deporte;
import dominio.Pais;
import dominio.TipoApuesta;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ColeccionesDAOImpl implements ColeccionesDAO {

	/**
	 * Queries
	 */
	private static final String BUSCAR_PAISES = "select p from Pais p";
	private static final String BUSCAR_DEPORTES = "select d from Deporte d";
	private static final String BUSCAR_COMPETICIONES = "select c from Competicion c";
	private static final String BUSCAR_COMPETICIONES_POR_DEPORTE_PAIS = "select c from Competicion c where c.deporte.id = :idDeporte and c.country.id = :idPais";
	private static final String BUSCAR_PARTICIPANTE_POR_NOMBRE = "select p from Participante p where p.name = :nombre";
	
	@javax.persistence.PersistenceContext(unitName="SUPERBET_UNIT" )
	private javax.persistence.EntityManager em;
		
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Pais> getPaises()
	{
		try
		{
			javax.persistence.Query query = em.createQuery(BUSCAR_PAISES);
			return (List<Pais>) query.getResultList();
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Deporte> getDeportes()
	{
		try
		{
			javax.persistence.Query query = em.createQuery(BUSCAR_DEPORTES);
			return query.getResultList();
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Competicion> getCompeticiones()
	{
		try
		{
			javax.persistence.Query query = em.createQuery(BUSCAR_COMPETICIONES);
			return query.getResultList();
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Deporte getDeporteByName(String name) 
	{
		try
		{
			return em.find(Deporte.class, name);
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Competicion> getCompeticionesPorDeportePais(Integer idDeporte, Integer idPais) {
		List<Competicion> competiciones = null;
		try
		{
			Query q2 = em.createQuery(BUSCAR_COMPETICIONES_POR_DEPORTE_PAIS);
			q2.setParameter("idDeporte", idDeporte);
			q2.setParameter("idPais", idPais);
			competiciones = (List<Competicion>)q2.getResultList();
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}
		return competiciones;
	}

	@Override
	public List<TipoApuesta> getTipoApuestas() {
		return (List<TipoApuesta>) em.createQuery("from TipoApuesta").getResultList();
		
	}
		
}
