package negocio.utiles.interfaces;

import javax.ejb.Local;

import dominio.Usuario;

@Local
public interface ServiciosSeguridad {

	public Usuario existeUsuario(String login ,String password );

	public void cambiarContrasenia(String txtMail) throws Exception;

}