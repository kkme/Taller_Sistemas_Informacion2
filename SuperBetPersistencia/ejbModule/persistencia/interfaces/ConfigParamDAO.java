package persistencia.interfaces;

import javax.ejb.Local;

import dominio.ConfigParam;

@Local
public interface ConfigParamDAO {

	public ConfigParam obtenerParametrosConfig();
	
}
