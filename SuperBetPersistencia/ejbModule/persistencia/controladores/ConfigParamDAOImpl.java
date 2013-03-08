package persistencia.controladores;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import dominio.ConfigParam;

import persistencia.interfaces.ConfigParamDAO;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConfigParamDAOImpl implements ConfigParamDAO {

	@javax.persistence.PersistenceContext(unitName = "SUPERBET_UNIT")
	private javax.persistence.EntityManager em;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ConfigParam obtenerParametrosConfig() {
		ConfigParam cp = null; 
		try {
			Query q = em.createQuery("select cp from ConfigParam cp");
			cp = (ConfigParam) q.getResultList().get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cp;
	}

}
