package managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utiles.GenericException;
import utiles.MessagePrinter;

import dominio.Usuario;

import negocio.usuarios.interfaces.IUsuarios;
import negocio.utiles.interfaces.ServiciosSeguridad;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/SuperBetNegocio/ServiciosSeguridadImpl!negocio.utiles.interfaces.ServiciosSeguridad")
	private ServiciosSeguridad servicioSeguridad;
	
	@EJB(lookup = "java:global/SuperBetNegocio/ControladorUsuarios!negocio.usuarios.interfaces.IUsuarios")
	private IUsuarios usrNegocio;

	private String txtLogin;
	private String txtPassword;

	@ManagedProperty(name = "guser", value = "#{usuarioMB}")
	private GestionUsuario guser;

	private boolean mostrarEnviarCorreo = false;

	private String txtMail;

	public String logout() {
		this.guser.setLogedUser(null);
		this.limpiarCampos();
		return "welcome";
	}

	private void limpiarCampos() {
		this.mostrarEnviarCorreo = false;
		this.txtLogin = "";
		this.txtMail = "";
		this.txtPassword = "";

	}

	public LoginBean() {
		System.out.println("Constructor LoginBean " + mostrarEnviarCorreo);
	}

	public String getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(String txtLogin) {
		this.txtLogin = txtLogin;
	}

	public String getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}

	public String enviarContrasenia() {
		if (txtMail == null) {
			MessagePrinter.showInformationMessage("Ingrese su correo");
		} else {

			try {
				servicioSeguridad.cambiarContrasenia(this.txtMail);
				MessagePrinter
						.showInformationMessage("Se envi� su contrase�a al correo registrado");
				mostrarEnviarCorreo = false;
				txtMail = "";
			} catch (Exception e) {
				System.out.println("Cacheo");
				MessagePrinter.showInformationMessage(e.getMessage());
			}
		}
		return null;
	}

	public String getTxtMail() {
		return txtMail;
	}

	public void setTxtMail(String txtMail) {
		this.txtMail = txtMail;
	}

	public String ingresar_ON_CLICK() {
		String outcome = null;
		try {
			Usuario us = servicioSeguridad.existeUsuario(this.txtLogin, this.txtPassword);
			if (us != null) {
				if (us.getState().equalsIgnoreCase("aconfirmar")) {
					MessagePrinter.showErrorMessage("Credenciales no confirmadas");
				} else {
					guser.setLogedUser(us);
					MessagePrinter.showInformationMessage("Credenciales correctas");
					guser.setBase64String(usrNegocio.obtenerBase64Avatar(us));
					outcome = us.getRole();
				}
			} else {
				MessagePrinter.showErrorMessage("Credenciales incorrectas");
			}
		} catch (Throwable ex) {
			MessagePrinter.showErrorMessage(ex.getMessage());
		}
		return outcome;
	}

	public void setGuser(GestionUsuario guser) {
		this.guser = guser;
	}

	public GestionUsuario getGuser() {
		return guser;
	}

	public void setMostrarEnviarCorreo(boolean mostrar) {
		System.out.println("Seteo MostrarEnviarCorreo a " + mostrar);
		this.mostrarEnviarCorreo = mostrar;
	}

	public boolean isMostrarEnviarCorreo() {
		return mostrarEnviarCorreo;
	}

}