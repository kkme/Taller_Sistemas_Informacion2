package servlets;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dominio.Usuario;

import managedbeans.GestionUsuario;

/**
 * Servlet Filter implementation class AccessControl
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, description = "Filtro utilizado para chequear el pedido de recursos.", urlPatterns = { "*.xhtml" })
public class AccessControl implements Filter {
	
	/*private String inicio="/";
	private String accesoNoAutenticado="/views/[a-zA-z0-9&&[^/]]*.xhtml";
	private String accesoUsuario="/views/public/[a-zA-z0-9&&[^/]]*.xhtml";
	private String accesoAdmin=".*";
	private String accesoModerador="/views/mod/[a-zA-z0-9&&[^/]]*.xhtml";
*/
    public AccessControl() {

    }

	public void destroy() {

	}
	
	
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	boolean acceso_autorizado=true;
	HttpServletRequest t = (HttpServletRequest)request;
	String url = t.getRequestURI(); 
	System.out.println("filtro url: "+url);
	if(t.getRequestURI().contains(".xhtml") && t.getRequestURI().contains("views")){
	
		String uri = t.getRequestURI().replace("/SuperBetWeb", "");
		HttpSession session = t.getSession();
		GestionUsuario gu = (GestionUsuario)session.getAttribute("usuarioMB");
		Usuario u =(gu!=null)?gu.getLogedUser():null;
		
		//Implementaci�n de pol�tica de control de acceso
		
		
		if(u==null){//usuario invitado 

			if(uri.contains("public")||uri.contains("mod")||uri.contains("admin") ){
				System.out.println("USUARIO NO AUTORIZADO");
				acceso_autorizado=false;
				t.getRequestDispatcher("/views/ForbiddenAccess.xhtml").forward(request, response);
			}
		}else if(u.getRole().equalsIgnoreCase("usuario")){//usuario apostador
			if(uri.contains("mod")||uri.contains("admin")){
				System.out.println("USUARIO NO AUTORIZADO");
				acceso_autorizado=false;
				t.getRequestDispatcher("/views/ForbiddenAccess.xhtml").forward(request, response);
			}
		}else if(u.getRole().equalsIgnoreCase("moderador")){
			if(uri.contains("admin") ){
				System.out.println("USUARIO NO AUTORIZADO");
				acceso_autorizado=false;
				t.getRequestDispatcher("/views/ForbiddenAccess.xhtml").forward(request, response);
			}
		}else if(u.getRole().equalsIgnoreCase("administrador")){
			/* Admin puede todo
			 * if(!(uri.matches(accesoAdmin) )){
				System.out.println("USUARIO NO AUTORIZADO");
			}*/
		}
	}
	if(acceso_autorizado){
	chain.doFilter(request, response);
	}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
