package persistencia.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;


import dominio.Apuesta;
import dominio.Combinacion;
import dominio.Competicion;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;
import dominio.externo.ApuestaExterna;

@Local
public interface ApuestaDAO {

	public List<TipoApuesta> getTiposApuesta();
	public void altaApuesta(Apuesta apuesta, Combinacion combinacion, List<Resultado> resultados, TipoApuesta tipoApuesta, Usuario user);
	public List<TipoEvento> getEventosExternos();
	public void altaApuestaCombinada(Usuario usuario,
			Combinacion combinacion, List<Resultado> resultados, Apuesta apuestas);
	public int altaApuestaMobile(Double monto, Integer idResultado,
			int resultadoExacto, Integer idUser);
	void registrarApuestaExterna(Integer idUsuario,Integer idApuestaExterna, Double monto, Double gnancias );
	void borrarApuestaExterna(Integer idApuestaExterna);
	public List<Apuesta> obtenerApuestasPagasUsuario(Integer idusuario);

}
