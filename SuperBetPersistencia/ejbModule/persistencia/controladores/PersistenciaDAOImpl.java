package persistencia.controladores;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import persistencia.interfaces.PersistenciaDAO;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersistenciaDAOImpl implements PersistenciaDAO {
	
	
	
	@javax.persistence.PersistenceContext(unitName="SUPERBET_UNIT" )
	private javax.persistence.EntityManager em;


}
