package negocio.utiles.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import negocio.utiles.Mailer;
import negocio.utiles.interfaces.ServiciosSeguridad;

import persistencia.interfaces.UsuarioDAO;
import utiles.ExceptionManager;
import utiles.GenericException;
import dominio.Usuario;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiciosSeguridadImpl implements ServiciosSeguridad {

	@EJB(lookup="java:global/SuperBetPersistencia/UsuarioDAOImpl!persistencia.interfaces.UsuarioDAO")
	private UsuarioDAO usuarioDAO;

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario existeUsuario(String login ,String password )
	{System.out.println("ServicioSeguridad:Consulto si existe usuario");
		try
		{
			List<Usuario> usuarios = this.usuarioDAO.buscarUsuarioPorLoginPassword(login, password);
			if(usuarios.isEmpty()) return null;
			else return usuarios.get(0);
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}

	@Override
	public void cambiarContrasenia(String txtMail) throws Exception {
		try{
		Usuario u = usuarioDAO.findByEmail(txtMail);
		Mailer.enviarMail(u.getEmail(), "NoReply: Clave SuperBet.com", "<h2>"+u.getPassword()+"</h2>");
		}catch(Exception e){
			throw e;
		}
		
	}

}