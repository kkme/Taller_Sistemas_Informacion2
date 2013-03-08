package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utiles.Constantes;
import dominio.Usuario;

import negocio.usuarios.interfaces.IUsuarios;

/**
 * Servlet implementation class ConfirmarUsuario
 */
@WebServlet("/ConfirmarUsuario")
public class ConfirmarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB(lookup = "java:global/SuperBetNegocio/ControladorUsuarios!negocio.usuarios.interfaces.IUsuarios")
	private IUsuarios usrNegocio;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("id");
		Usuario usr = usrNegocio.confirmarUsuario(param);
		String redirectURL;
		System.out.println("entre al servlet Confirmar Usuario");
		if((usr != null) && (usr.getState().equals("aconfirmar"))){
			System.out.println("confirme el usuario " + usr.getNames());
			usr.setState("registered");
			usrNegocio.updateUser(usr);
			redirectURL = "http://"+Constantes.DOMINIO()+":"+Constantes.PUERTO+"/SuperBetWeb/";
		} else {
			redirectURL = "http://"+Constantes.DOMINIO()+":"+Constantes.PUERTO+"/SuperBetWeb/views/error.xhtml";
		}
		response.sendRedirect(redirectURL);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void setUsrNegocio(IUsuarios usrNegocio) {
		this.usrNegocio = usrNegocio;
	}

	public IUsuarios getUsrNegocio() {
		return usrNegocio;
	}

}
