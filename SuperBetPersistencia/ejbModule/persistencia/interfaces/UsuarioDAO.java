package persistencia.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import dominio.Apuesta;
import dominio.Usuario;

@Local
public interface UsuarioDAO {

	public Usuario insert(Usuario entity );

	public Usuario update(Usuario entity );

	public void delete(Usuario entity );

	public void delete(Integer id );

	public Usuario findById(Integer id );

	public List<Usuario> findAll();

	public List<Usuario> buscarUsuarioPorLoginPassword(String login ,String password );

	public Usuario findByEmail(String email) throws Exception;
	
	public Usuario findByCedula(String cedula) throws Exception;
	
	public double getMonto(int id_user);

	public ArrayList<Apuesta> getApuestas(Usuario logedUser);

	public List<Usuario> obtenerModeradores();

	public void altaNuevaParteAvatar(Usuario logedUser, int idSec, String part);
	
	public List<String> obtenerPartesAvatar (Usuario logedUser);

	public void eliminarAvatar(Usuario logedUser);

	public boolean existeMail(String email);

}