package negocio.moderadores.interfaces;

import java.util.List;

import javax.ejb.Local;

import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;
@Local
public interface IModeradores {

	List<TipoApuesta> listaTiposApuestasNoPagosDeEvento(Evento pagar_selectedEvent);

	List<Resultado> listarResultados(TipoApuesta pagar_selectedTipoApuesta,
			Evento pagar_selectedEvent);

	void pagarApuestas(Evento pagar_selectedEvent,
			TipoApuesta pagar_selectedTipoApuesta,
			Resultado pagar_selectedResult);
	
	
}
