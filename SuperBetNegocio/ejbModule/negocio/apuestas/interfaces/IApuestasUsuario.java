package negocio.apuestas.interfaces;

import java.util.List;

import javax.ejb.Local;

import dominio.Apuesta;
import dominio.Combinacion;
import dominio.Competicion;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;

@Local
public interface IApuestasUsuario {

	public List<TipoApuesta> getTiposApuesta();
	public void altaApuesta(Apuesta apuesta, Combinacion combinacion, List<Resultado> resultados, TipoApuesta tipoApuesta, Usuario user);
	public List<TipoEvento> getEventosExternos();
	public void altaApuestaCombinada(Usuario usuario,
			Combinacion combinacion, List<Resultado> resultados, Apuesta apuesta);
	public int altaApuestaMobile(Double monto, Integer idResultado,
			int resultadoExacto, Integer idUser);

}
