package negocio.utiles.interfaces;

import java.util.List;

import javax.ejb.Local;

import dominio.Competicion;
import dominio.ConfigParam;
import dominio.Deporte;
import dominio.Pais;
import dominio.TipoApuesta;

@Local
public interface Colecciones {

	public List<Pais> getPaises();
	
	public List<Competicion> getCompeticiones();
	
	public List<Deporte> getDeportes();

	public List<Competicion> getCompeticionesPorDeportePais(Integer idDeporte, Integer idPais);

	public List<TipoApuesta> getTipoApuestas();

	public ConfigParam obtenerParamsConfig();
	
}
