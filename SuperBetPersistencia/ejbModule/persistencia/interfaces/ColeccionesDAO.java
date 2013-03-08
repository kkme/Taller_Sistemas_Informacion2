package persistencia.interfaces;

import java.util.List;

import javax.ejb.Local;

import dominio.Competicion;
import dominio.Deporte;
import dominio.Pais;
import dominio.TipoApuesta;

@Local
public interface ColeccionesDAO {

	public List<Pais> getPaises();
	public List<Deporte> getDeportes();
	public List<Competicion> getCompeticiones();
	public Deporte getDeporteByName(String name);
	public List<Competicion> getCompeticionesPorDeportePais(Integer idDeporte, Integer idPais);
	public List<TipoApuesta> getTipoApuestas();
	
}
