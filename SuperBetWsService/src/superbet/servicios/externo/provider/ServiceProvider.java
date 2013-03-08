package superbet.servicios.externo.provider;

import java.util.List;
import superbet.servicios.externo.datatypes.DataEvento;
import superbet.servicios.externo.datatypes.DataGanador;
import superbet.servicios.externo.datatypes.DataResultado;

public interface ServiceProvider {
 
	/**
	 * Estas operaciones son utilizadas para permitir apuestas desde otro sistema
	 * 
	 */
	//CU APOSTAR ***************
	DataEvento[] listarEventosSimples();
	
	public List<DataResultado> listarResultados( Integer idEvento);
	
	public String apostar( Integer idUser,Integer idEvento ,  Integer idResultado,  Double monto );
	//**************************
	
	
	
	public List<DataGanador> obtenerApuestasPagasUsuario(Integer idUsuario);
	
	
	
	//CU PAGAR *****************
	/**
	 * A trav�s de esta operaci�n
	 * El sistema externo informa cuales fueron las apuestas ganadoras
	 * @param apuestasGanadoras
	 * @throws Exception 
	 */
	public void enviarResultadosGanadores(DataGanador[] apuestasGanadoras) throws Exception;
	//**************************
	
	
}
