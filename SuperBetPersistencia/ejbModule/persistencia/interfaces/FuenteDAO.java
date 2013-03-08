package persistencia.interfaces;

import java.util.List;
import javax.ejb.Local;

import utiles.Multimedia;
import dominio.Competicion;
import dominio.Fuente;

@Local
public interface FuenteDAO {

	public void altaFuente(Fuente fuente);

	public List<Fuente> obtenerTodasFuentes();

}
