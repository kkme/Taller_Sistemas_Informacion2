package negocio.usuarios.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import dominio.Apuesta;
import dominio.ParteAvatar;
import dominio.Usuario;

import persistencia.interfaces.UsuarioDAO;
import utiles.Constantes;
import negocio.usuarios.interfaces.IModeradoresAdmin;
import negocio.usuarios.interfaces.IUsuarios;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ControladorUsuarios implements IUsuarios, IModeradoresAdmin{
	
	//java:global[/<app-name>]/<module-name>/<beanname>[!<fully-qualified-interface-name>]
	@EJB(lookup="java:global/SuperBetPersistencia/UsuarioDAOImpl!persistencia.interfaces.UsuarioDAO")
	private UsuarioDAO usuarioDAO;

	@Override
	public double getMonto(int id_user) {
		return usuarioDAO.getMonto(id_user);
	}

	@Override
	public void insertarModerador(Usuario usuario) {
		usuarioDAO.insert(usuario);
	}

	@Override
	public void insertarUsuario(Usuario usuario) {
		usuarioDAO.insert(usuario);
	}
	
	@Override
	public Usuario updateUser(Usuario logedUser) {
		return usuarioDAO.update(logedUser);
	}

	@Override
	public Usuario confirmarUsuario(String cedula) {
		try {
			Usuario ret = usuarioDAO.findByCedula(cedula);
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Apuesta> getApuestas(Usuario logedUser) {
		return usuarioDAO.getApuestas(logedUser);
	}

	@Override
	public List<Usuario> obtenerModeradores() {
		return usuarioDAO.obtenerModeradores();
	}

	@Override
	public void altaBase64Avatar(Usuario logedUser, String base64String) {
		int tamRestante = base64String.length(), 
		    indiceInic = 0, indiceFin = Constantes.TAM_FILE_BUFF, idSec = 0; 
		List<ParteAvatar> partes = new ArrayList<ParteAvatar>();
		do {
			String part = base64String.substring(indiceInic,indiceFin);
			ParteAvatar pAvatar = new ParteAvatar();
			pAvatar.setContParteAvatar(part);
			pAvatar.setIdSecuencia(idSec);
			partes.add(pAvatar);
			
			// actualizo indices

			tamRestante = tamRestante - Constantes.TAM_FILE_BUFF;
			indiceInic = indiceFin;
			indiceFin = (Constantes.TAM_FILE_BUFF > tamRestante)? indiceFin + tamRestante : 
																			  indiceFin + Constantes.TAM_FILE_BUFF;
			idSec++;
			
		} while (tamRestante > 0);
		
		for (ParteAvatar pAvatar : partes){
			usuarioDAO.altaNuevaParteAvatar(logedUser,pAvatar.getIdSecuencia(),pAvatar.getContParteAvatar());
		}
	}

	@Override
	public String obtenerBase64Avatar(Usuario logedUser) {
		String base64Avatar = "";
		List<String> partesAvatar = usuarioDAO.obtenerPartesAvatar(logedUser);
		if (partesAvatar != null) {
			for (String pa : partesAvatar) {
				base64Avatar += pa;
			}
		}
		return base64Avatar;
	}
	@Override
	public boolean existeMail(String email) {
		return usuarioDAO.existeMail(email);
	}


	@Override
	public void eliminarAvatar(Usuario logedUser) {
		usuarioDAO.eliminarAvatar(logedUser);
	}

	

	
}
