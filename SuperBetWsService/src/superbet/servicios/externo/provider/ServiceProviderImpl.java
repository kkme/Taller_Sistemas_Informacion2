package superbet.servicios.externo.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import negocio.apuestas.interfaces.IApuestasUsuarioRemote;
import negocio.eventos.interfaces.IEventosAdminRemote;
import superbet.servicios.externo.datatypes.DataEvento;
import superbet.servicios.externo.datatypes.DataGanador;
import superbet.servicios.externo.datatypes.DataResultado;
import utiles.Constantes;
import dominio.Apuesta;
import dominio.Resultado;
import dominio.TipoEvento;

@WebService(wsdlLocation="wsdl/SuperBetService.wsdl")//esto hay que cambiarlo cuando se deploya uno nuevo
public class ServiceProviderImpl implements ServiceProvider {
	 
	public static void main(String args[] ){
		
		ServiceProviderImpl ser = new ServiceProviderImpl();
		
		for(DataEvento de :ser.listarEventosSimples()) System.out.println(de.getNombre()+"   ");
		
		for(DataResultado dr: ser.listarResultados(2)) System.out.println(dr.getDescripcion()+"   ");
		
		System.out.println(ser.apostar(2,2,14,100.0));
		//idRes=12-18
		
		
		
	}


	private IEventosAdminRemote eventosAdmin;
	

	private IApuestasUsuarioRemote apuestasUsuario;

	
	public ServiceProviderImpl() {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");
		Context context;
		try {
			context = new InitialContext(properties);
			eventosAdmin = (IEventosAdminRemote) context.lookup("ControladorEventos/remote");
			apuestasUsuario= (IApuestasUsuarioRemote) context.lookup("ControladorApuestas/remote");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}		

	
	
	@WebMethod
	public @WebResult DataEvento[] listarEventosSimples() {
		List<TipoEvento> eventos = eventosAdmin.tipoEventosNoPagos();
		List<DataEvento> resultado = new ArrayList<DataEvento>();
		for(TipoEvento te: eventos){
			
			if(te.getTipoApuesta().getId() == Constantes.RESULTADO_EXACTO){
			
			DataEvento ev = new DataEvento();
			ev.setId(te.getEvento().getId());
			ev.setDescripcion(te.getEvento().getCompeticion().getNombre()+" : "+te.getEvento().getNombre()+" finaliza: "+te.getFinPerApuesta());
			ev.setLatitud(te.getEvento().getUbicacion().getLatitud());
			ev.setLongitud(te.getEvento().getUbicacion().getLongitud());
			ev.setNombre(te.getEvento().getNombre());
			resultado.add(ev);
			}
			
		}
		
		DataEvento[] r = new DataEvento[resultado.size()];
		r = resultado.toArray(r);
		
		return r;
	}

	
	
	@Override
	@WebMethod
	public @WebResult List<DataResultado> listarResultados(@WebParam(name="idEvento",partName="idEvento") Integer idEvento) {
		List<Resultado> res = eventosAdmin.getResultados(idEvento , Constantes.RESULTADO_EXACTO);
		
		List<DataResultado> resultado = new ArrayList<DataResultado>();
		
		for(Resultado e: res){
			DataResultado dr = new DataResultado();
			dr.setDividendo(e.getCuota());
			dr.setDescripcion(e.getDescription());
			dr.setId(e.getId());
			
			resultado.add(dr);
		}
		
		return resultado;
	}

	//el id del usuario externo que apuesta es g05
	@Override
	@WebMethod
	public String apostar(@WebParam(name="idUser",partName="idUser") Integer idUser, @WebParam(name="idEvento",partName="idEvento") Integer idEvento,
			@WebParam(name="idResultado", partName="idResultado") Integer idResultado, @WebParam(name="monto",partName="monto") Double monto) {
		Integer resultado =apuestasUsuario.altaApuestaMobile(monto, idResultado, Constantes.RESULTADO_EXACTO, idUser);
		
		return resultado.toString();
	}
	
	@Override
	@WebMethod
	public void enviarResultadosGanadores(@WebParam DataGanador[] apuestasExternas) throws Exception{
		//Una vez que recibo los resultados Ganadores
		//debo pagarles y borrarlos de la tabla temporal
		for(int i=0;i<apuestasExternas.length;i++){
			DataGanador d = apuestasExternas[i];
			int idUsuario = d.getIdUsuario();
			int idApuesta = d.getIdApuesta();
			Double ganancia = d.getGanancia();
			eventosAdmin.pagarEventoExterno(idUsuario, idApuesta, ganancia );
		}
		
	}



	@Override
	@WebMethod
	public @WebResult List<DataGanador> obtenerApuestasPagasUsuario(@WebParam(name="idUsuario") Integer idUsuario) {
		List<Apuesta> apuestas = apuestasUsuario.obtenerApuestasPagasUsuario(idUsuario);
		
		List<DataGanador> resultado = new ArrayList<DataGanador>();
		
		for(Apuesta a :apuestas){
			DataGanador dg = new DataGanador();
			dg.setGanancia(a.getGanancia());
			dg.setIdApuesta(a.getId());
			dg.setIdUsuario(a.getUser().getId());
			resultado.add(dg);
		}
		
		return resultado;
	}
	
	
	
	
}
