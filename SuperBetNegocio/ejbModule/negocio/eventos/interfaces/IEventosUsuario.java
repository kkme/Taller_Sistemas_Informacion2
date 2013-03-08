package negocio.eventos.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import dominio.Combinacion;
import dominio.Competicion;
import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Ubicacion;

@Local
public interface IEventosUsuario {

	public List<TipoEvento> obtenerTipoEventosCondicion(String tipoApuesta, String deporte,
			String pais, String competencia);
	public Combinacion obtenerCombinacionTiposEvento(Collection<TipoEvento> tiposEvento);

}
