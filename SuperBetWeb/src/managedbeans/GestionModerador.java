package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FlowEvent;

import utiles.Constantes;
import utiles.MessagePrinter;

import negocio.moderadores.interfaces.IModeradores;
import negocio.usuarios.interfaces.IModeradoresAdmin;
import dominio.Evento;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;

@ManagedBean(name="moderadorMB")
@SessionScoped
public class GestionModerador {
	
	private String names;
	private String surnames;
	private String userName;
	private String password;
	
	@EJB(lookup="java:global/SuperBetNegocio/ControladorUsuarios!negocio.usuarios.interfaces.IModeradoresAdmin")
	private IModeradoresAdmin moderadoresAdmin;
	
	@EJB(lookup="java:global/SuperBetNegocio/ControladorModeradores!negocio.moderadores.interfaces.IModeradores")
	private IModeradores cModeradores;
	
	private Evento pagar_selectedEvent;
	private List<TipoApuesta> pagar_tipoApuestas=new ArrayList<TipoApuesta>();
	private TipoApuesta pagar_selectedTipoApuesta;
	private List<Resultado> pagar_resultados= new ArrayList<Resultado>();
	private Resultado pagar_selectedResult;
	
	@SuppressWarnings("unused")
	private List<Usuario> moderadores;
	
	private Usuario moderadorSelected;
	
	public String pagar_cargarTipoApuestas(){
		if(pagar_selectedEvent!= null){
			pagar_tipoApuestas = cModeradores.listaTiposApuestasNoPagosDeEvento(pagar_selectedEvent);
		}
		return null;
	}
	
	public String pagar_cargarResultados(){
		if(pagar_selectedTipoApuesta!=null){
			pagar_resultados = cModeradores.listarResultados(pagar_selectedTipoApuesta, pagar_selectedEvent);
		}
		return null;
	}
	
	public String pagar_pagarApuestas(){
		try{
		cModeradores.pagarApuestas(this.pagar_selectedEvent,this.pagar_selectedTipoApuesta,this.pagar_selectedResult);
		limpiarCampos();
		MessagePrinter.showInformationMessage("Se han pagado las apuestas");
		}catch(Exception e){
			e.printStackTrace();
			MessagePrinter.showErrorMessage(e.getMessage());
		}
		return "moderador";
	}
	
	public String limpiarCampos() {
		this.pagar_resultados.clear();
		this.pagar_selectedEvent=null;
		this.pagar_selectedResult=null;
		this.pagar_selectedTipoApuesta=null;
		this.pagar_tipoApuestas.clear();
		//TODO: faltan otros campos 
		return "moderador";
	}

	public Evento getPagar_selectedEvent() {
		return pagar_selectedEvent;
	}

	public void setPagar_selectedEvent(Evento pagar_selectedEvent) {
		this.pagar_selectedEvent = pagar_selectedEvent;
	}

	//Para wizard pago apuesta
    public String onFlowPagar(FlowEvent event) {  
        System.out.println("Current wizard step:" + event.getOldStep());  
        System.out.println("Next step:" + event.getNewStep());  
            return event.getNewStep();  
     }

	public void setNames(String names) {
		this.names = names;
	}
	public String getNames() {
		return names;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String altaModerador () {
		System.out.println("Alta de moderador...");
		try{
			Usuario moderador = new Usuario();
			moderador.setNames(names);
			names = "";
			moderador.setSurnames(surnames);
			surnames = "";
			moderador.setUserName(userName);
			userName = "";
			moderador.setPassword(password);
			password = "";
			moderador.setRole(Constantes.ROL_MOD);
			System.out.println("Se creo la instancia de usuario...");
			moderador.setState(Constantes.ESTADO_REG); 
			moderadoresAdmin.insertarModerador(moderador);
			MessagePrinter.showInformationMessage("Se ha creado un usuario moderador");
		}catch(Exception e){
			MessagePrinter.showErrorMessage("Error al crear usuario moderador");
		}
		return "moderadores";
	}
	
	public String editarModerador () {
		return "editarModerador";
	}

	public void setPagar_tipoApuestas(List<TipoApuesta> pagar_tipoApuestas) {
		this.pagar_tipoApuestas = pagar_tipoApuestas;
	}

	public List<TipoApuesta> getPagar_tipoApuestas() {
		//TODO: esto esta para probar
		pagar_cargarTipoApuestas();
		return pagar_tipoApuestas;
	}

	public void setPagar_selectedTipoApuesta(TipoApuesta pagar_selectedTipoApuesta) {
		this.pagar_selectedTipoApuesta = pagar_selectedTipoApuesta;
	}

	public TipoApuesta getPagar_selectedTipoApuesta() {
		return pagar_selectedTipoApuesta;
	}

	public void setPagar_resultados(List<Resultado> pagar_resultados) {
		this.pagar_resultados = pagar_resultados;
	}

	public List<Resultado> getPagar_resultados() {
		return pagar_resultados;
	}

	public void setPagar_selectedResult(Resultado pagar_selectedResult) {
		this.pagar_selectedResult = pagar_selectedResult;
	}

	public Resultado getPagar_selectedResult() {
		return pagar_selectedResult;
	}

	public void setModeradores(List<Usuario> moderadores) {
		this.moderadores = moderadores;
	}

	public List<Usuario> getModeradores() {
		return moderadoresAdmin.obtenerModeradores();
	}

	public void setModeradorSelected(Usuario moderadorSelected) {
		this.moderadorSelected = moderadorSelected;
	}

	public Usuario getModeradorSelected() {
		return moderadorSelected;
	}

}
