package superbet.rest.cliente;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import superbet.rest.datatypes.DataEvento;
import superbet.rest.datatypes.DataResultado;


@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface MobileClient {
	@PUT
	@Path("/login/")
	public Integer login(@QueryParam("user") String user,  @QueryParam("pass") String passwd, @QueryParam("host") String host);

	@GET
	@Path("/eventos/")
	public List<DataEvento> listarEventos(@QueryParam("host") String host);

	@GET
	@Path("{idEvento}/resultados/")
	public List<DataResultado> listarResultados(@PathParam("idEvento") Integer idEvento, @QueryParam("host") String host);
	
	@PUT
	@Path("/apostar/")
	public String apostar(@QueryParam("user") Integer idUser,@QueryParam("idEvento")Integer idEvento , @QueryParam("result") Integer idResultado, @QueryParam("monto") Double monto, @QueryParam("host") String host);
}
