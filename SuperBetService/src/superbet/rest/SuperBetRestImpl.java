package superbet.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import negocio.apuestas.interfaces.IApuestasUsuario;
import negocio.apuestas.interfaces.IApuestasUsuarioRemote;
import negocio.eventos.interfaces.IEventosAdmin;
import negocio.eventos.interfaces.IEventosAdminRemote;
import negocio.utiles.interfaces.ServiciosSeguridad;
import superbet.rest.datatypes.DataEvento;
import superbet.rest.datatypes.DataResultado;
import utiles.Constantes;
import dominio.Resultado;
import dominio.TipoEvento;
import dominio.Usuario;


@Path("/rest")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class SuperBetRestImpl {
	
	
	
	
	@EJB(lookup="java:global/SuperBetNegocio/ServiciosSeguridadImpl!negocio.utiles.interfaces.ServiciosSeguridad")
	private ServiciosSeguridad servicioSeguridad;
	@EJB(lookup = "java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdminRemote eventosAdmin;
	@EJB(lookup = "java:global/SuperBetNegocio/ControladorApuestas!negocio.apuestas.interfaces.IApuestasUsuario")
	private IApuestasUsuarioRemote apuestasUsuario;

	
	
	public SuperBetRestImpl(){
	
	Properties properties = new Properties();
	properties.put("java.naming.factory.initial",
			"org.jnp.interfaces.NamingContextFactory");
	properties.put("java.naming.factory.url.pkgs",
			"org.jboss.naming:org.jnp.interfaces");
	properties.put("java.naming.provider.url", "localhost:1099");
	Context context;
	try {
		context = new InitialContext(properties);
		eventosAdmin		= (IEventosAdminRemote) 	context.lookup("ControladorEventos/remote")		;
		apuestasUsuario		= (IApuestasUsuarioRemote)	context.lookup("ControladorApuestas/remote")	;
		servicioSeguridad	= (ServiciosSeguridad)		context.lookup("ServiciosSeguridadImpl/local")	;
	} catch (NamingException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}		

	
	

	
	

	//Devuelve idUsuario, -1 es falla login
@PUT
@Path("/login/")
public Integer login(@QueryParam("user") String user,  @QueryParam("pass") String passwd){
	try{
	Usuario u = servicioSeguridad.existeUsuario(user, passwd);
	if(u==null)return -1;
	System.out.println("REST_impl::login devuelve "+u.getId());
	return u.getId();
	
	}catch(Exception e){
		e.printStackTrace();
		return -1;
	}
	
	
	
}

@GET
@Path("/eventos/")
public List<DataEvento> listarEventos(){
	
	
	/*
	DataEvento de[] = new DataEvento[2];
	de[0] = new DataEvento();
	de[0].setId(1);
	de[0].setNombre("nombre");
	de[1] = new DataEvento();
	de[1].setId(2);
	de[1].setNombre("nombre2");

	List<DataEvento> res = new ArrayList<DataEvento>();
	
	res.add(de[0]);
	res.add(de[1]);
	*/
	
	List<TipoEvento> eventos = eventosAdmin.tipoEventosNoPagos();
	List<DataEvento> resultado = new ArrayList<DataEvento>();
	for(TipoEvento te: eventos){
		
		if(te.getTipoApuesta().getId() == Constantes.RESULTADO_EXACTO){
		
		DataEvento ev = new DataEvento();
		ev.setId(te.getEvento().getId());
		ev.setNombre(te.getEvento().getNombre());
		resultado.add(ev);
		}
		
	}
	
	return resultado;
}

@GET
@Path("{idEvento}/resultados/")
public List<DataResultado> listarResultados(@PathParam("idEvento") Integer idEvento){

	List<Resultado> res = eventosAdmin.getResultados(idEvento , Constantes.RESULTADO_EXACTO);
	
	List<DataResultado> resultado = new ArrayList<DataResultado>();
	
	for(Resultado e: res){
		DataResultado dr = new DataResultado();
		dr.setCuota(e.getCuota());
		dr.setDescripcion(e.getDescription());
		dr.setId(e.getId());
		resultado.add(dr);
	}
	
	return resultado;
}

//Devuelve descripci�n del resultado de la apuesta
@PUT
@Path("/apostar/")
public String apostar(@QueryParam("user") Integer idUser,@QueryParam("idEvento")Integer idEvento , @QueryParam("result") Integer idResultado, @QueryParam("monto") Double monto ){
	
	apuestasUsuario.altaApuestaMobile(monto, idResultado, Constantes.RESULTADO_EXACTO, idUser);
	
	return "Exito";
}

}
