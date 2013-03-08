package persistencia.controladores;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dominio.Fuente;
import persistencia.interfaces.FuenteDAO;
import utiles.ExceptionManager;
import utiles.Multimedia;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FuenteDAOImpl implements FuenteDAO {

	private static String OBTENER_TODAS_FUENTES = "select f from Fuente f";
	private static String OBTENER_VIDEOS_DESTACADOS = "select m from Multimedia m where m.tipo = 1";
	
	@javax.persistence.PersistenceContext(unitName="SUPERBET_UNIT" )
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void altaFuente(Fuente fuente) {
		try {
			em.persist(fuente);
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
	}

	@Override
	public List<Fuente> obtenerTodasFuentes() {
		List<Fuente> fuentes = null;
		try {
			Query q = em.createQuery(OBTENER_TODAS_FUENTES);
			fuentes = q.getResultList();
		} catch (Throwable ex) {
			throw ExceptionManager.process(ex);
		}
		return fuentes;
	}

}
