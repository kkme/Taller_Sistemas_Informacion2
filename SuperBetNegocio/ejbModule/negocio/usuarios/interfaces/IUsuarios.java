package negocio.usuarios.interfaces;

import java.util.ArrayList;

import javax.ejb.Local;

import dominio.Apuesta;
import dominio.Usuario;

@Local
public interface IUsuarios {
	public void insertarUsuario(Usuario usuario);
	public Usuario confirmarUsuario(String cedula);
	public Usuario updateUser(Usuario logedUser);
	public double getMonto (int id_user);
	public ArrayList<Apuesta> getApuestas(Usuario logedUser);
	public void altaBase64Avatar(Usuario logedUser, String base64String);
	public String obtenerBase64Avatar (Usuario logedUser);
	public void eliminarAvatar(Usuario logedUser);   
	public boolean existeMail(String email);

}
