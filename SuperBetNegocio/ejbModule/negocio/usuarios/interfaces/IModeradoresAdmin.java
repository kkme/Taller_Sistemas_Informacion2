package negocio.usuarios.interfaces;

import java.util.List;

import javax.ejb.Local;

import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.Usuario;

@Local
public interface IModeradoresAdmin {
	public void insertarModerador(Usuario usuario);
	public List<Usuario> obtenerModeradores();
}
