package negocio.utiles.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import negocio.utiles.interfaces.Colecciones;
import persistencia.interfaces.ColeccionesDAO;
import persistencia.interfaces.ConfigParamDAO;
import dominio.Competicion;
import dominio.ConfigParam;
import dominio.Deporte;
import dominio.Pais;
import dominio.TipoApuesta;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ColeccionesImpl implements Colecciones {
	
	@EJB(lookup="java:global/SuperBetPersistencia/ColeccionesDAOImpl!persistencia.interfaces.ColeccionesDAO")
	private ColeccionesDAO coleccionesDAO;
	
	@EJB(lookup="java:global/SuperBetPersistencia/ConfigParamDAOImpl!persistencia.interfaces.ConfigParamDAO")
	private ConfigParamDAO configParamDAO;

	@Override
	public List<Pais> getPaises() {
		return coleccionesDAO.getPaises();
	}

	@Override
	public List<Competicion> getCompeticiones() {
		return coleccionesDAO.getCompeticiones();
	}

	@Override
	public List<Deporte> getDeportes() {
		return coleccionesDAO.getDeportes();
	}

	@Override
	public List<Competicion> getCompeticionesPorDeportePais(Integer idDeporte, Integer idPais) {
		return coleccionesDAO.getCompeticionesPorDeportePais(idDeporte, idPais);
	}


	@Override
	public List<TipoApuesta> getTipoApuestas() {
		
		return coleccionesDAO.getTipoApuestas();
	}

	@Override
	public ConfigParam obtenerParamsConfig() {
		return configParamDAO.obtenerParametrosConfig();
	}

}
