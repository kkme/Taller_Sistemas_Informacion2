package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utiles.Constantes;

import managedbeans.GestionUsuario;

/**
 * Servlet implementation class ComunicacionPayPal
 */
@WebServlet("/ComunicacionPayPal")
public class ComunicacionPayPal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */

    public ComunicacionPayPal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("transaction"); 
		if(param.equalsIgnoreCase("true")){
			//Pago confirmado ingreso dinero a la cuenta de usuario logueado
			HttpSession session = request.getSession();
			GestionUsuario gu = (GestionUsuario)session.getAttribute("usuarioMB");
			//System.out.println(gu);
			gu.ConfirmarPago();
		}
		
		String redirectURL = "http://"+Constantes.DOMINIO()+":"+Constantes.PUERTO+"/SuperBetWeb/views/public/usuarios/perfil.xhtml?transaction="+param;
		response.sendRedirect(redirectURL);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
