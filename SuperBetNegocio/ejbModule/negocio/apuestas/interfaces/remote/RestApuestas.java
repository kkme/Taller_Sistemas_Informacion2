package negocio.apuestas.interfaces.remote;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;


//@Path(value="/Hello")
@Stateless
public class RestApuestas implements negocio.apuestas.interfaces.IApuestasMovil{
	
	@GET
	@Produces("text/html")
	public String getHtml() {
	    return "<html lang=\"en\"><body><h1>Hello, World!!</body></h1></html>";
	}
	
	 public static void main(String[] args) throws Exception 
	   {
	      TJWSEmbeddedJaxrsServer tjws = new TJWSEmbeddedJaxrsServer();
	      tjws.setPort(8081);
	  //    tjws.getRegistry().addPerRequestResource(RestApuestas.class);
	      tjws.start();
	   }

}
