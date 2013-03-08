package managedbeans;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

import negocio.eventos.interfaces.IEventosAdmin;
import utiles.Constantes;
import utiles.MessagePrinter;
import utiles.Utiles;
import dominio.Competicion;
import dominio.Evento;
import dominio.Resultado;
import dominio.TipoEvento;

@ManagedBean(name="gestionResultado")
@SessionScoped
public class GestionResultado {
	
	@EJB(lookup="java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdmin eventosAdmin;
	
	@ManagedProperty(value = "#{gestionEvento}")
	private GestionEvento gestionEvento;
	
	@ManagedProperty(value = "#{coleccionesBean}")
	private ColeccionesBean coleccionesBean;
	
	private String nombreTipoApuestaSelected;

	private ArrayList<Resultado> resultados = new ArrayList<Resultado>();
	
	private Integer idTipoApuesta;
	
	private Resultado nuevoResultado = new Resultado();
	
	private Resultado resultadoAEliminar=null;
	
	public void eliminarResultado(){
		System.out.println("elimino resultado");
		resultados.remove(resultadoAEliminar);
	}
			 
    public String recargar() {     	
        return null;  
    } 
        
    public String borrarResultados(){
    	resultados.clear();
    	return null;
    }
    
    
    
    
    public void agregarResultado(ActionEvent e){
    	boolean error = false;
    	
    	if (nuevoResultado.getCuota() == null) {
    		UIInput component = (UIInput) Utiles.findComponentInRoot("inputCuota");
    		component.setValid(false);
			FacesMessage message = new FacesMessage("Por favor ingrese un valor a este campo");
			FacesContext.getCurrentInstance().addMessage("inputCuota", message);
    		error = true;
    	} else if (nuevoResultado.getCuota() < 1) {
    		UIInput component = (UIInput) Utiles.findComponentInRoot("inputCuota");
    		component.setValid(false);
			FacesMessage message = new FacesMessage("Por favor ingrese un valor mayor o igual a 1");
			FacesContext.getCurrentInstance().addMessage("inputCuota", message);
			error = true;
    	}
    	if ((nuevoResultado.getDescription() == null) || (nuevoResultado.getDescription().length() == 0)) {
    		UIInput component = (UIInput) Utiles.findComponentInRoot("inputDesc");
    		component.setValid(false);
			FacesMessage message = new FacesMessage("Por favor ingrese un valor a este campo");
			FacesContext.getCurrentInstance().addMessage("inputDesc", message);
    		error = true;
    	} 
    	if (idTipoApuesta < 0) {
    		UIInput component = (UIInput) Utiles.findComponentInRoot("comboTipoApuesta");
    		component.setValid(false);
			FacesMessage message = new FacesMessage("Por favor seleccione un valor");
			FacesContext.getCurrentInstance().addMessage("comboTipoApuesta", message);
			error = true;
    	}
    	
    	if (!error) {
    		System.out.println("agregue: "+nuevoResultado.getDescription());
	    	resultados.add(nuevoResultado);
	    	nuevoResultado = new Resultado();  
    	}
    }
	

	public void setGestionEvento (GestionEvento gestionEvento) {
		this.gestionEvento = gestionEvento;
	}
	
	public void setColeccionesBean (ColeccionesBean coleccionesBean) {
		this.coleccionesBean = coleccionesBean;
	}
	
	public String editarResultadosEvento () {
		Evento evento = gestionEvento.getEventoSelected();
		if (evento != null) {
			try{
				Competicion competicion = gestionEvento.obtenerCompeticionDeEvento(evento.getId());
				evento.setCompeticion(competicion);
			} catch(Exception e) {
				System.out.println("Error al traer competicion");
				evento.setCompeticion(null);
			}
		}
		return "editarResultados";
	}

	public void preCargarResultados(){
		

		
		//cargo resultados para evento-tipoApuesta		
		TipoEvento preExistente = eventosAdmin.getTipoEvento(gestionEvento.getEventoSelected().getId(),idTipoApuesta);
		if (preExistente != null) {
			this.resultados = eventosAdmin.getResultados(gestionEvento.getEventoSelected().getId(),idTipoApuesta);
		} else {
			this.resultados = new ArrayList<Resultado>();

		}

	}

	public String altaResultados () {
		
		String ret = null;
		
		try{
			if ((resultados != null) && (!resultados.isEmpty())) {
				gestionEvento.getFechaInicioApuesta().set(3,gestionEvento.getHoraInicioApuesta());
				gestionEvento.getFechaInicioApuesta().set(4,gestionEvento.getMinInicioApuesta());
				gestionEvento.getFechaFinApuesta().set(3,gestionEvento.getHoraFinApuesta());
				gestionEvento.getFechaFinApuesta().set(4,gestionEvento.getMinFinApuesta());		
				eventosAdmin.insertarNuevosResultados(gestionEvento.getEventoSelected(),idTipoApuesta,resultados,gestionEvento.getFechaInicioApuesta().getTime(),gestionEvento.getFechaFinApuesta().getTime());
				MessagePrinter.showInformationMessage("Se ingresaron resultados con �xito");
				limpiarCampos();
				ret = "resultados";
			} else {
				FacesMessage message = new FacesMessage("Debe ingresar resultados");
				FacesContext.getCurrentInstance().addMessage("message", message);
			}
		} catch(Exception e) {
			MessagePrinter.showErrorMessage("Error al ingresar los resultados");
		}
		
		return ret;
	}

	private void limpiarCampos() {
		resultados.clear();
		gestionEvento.setFechaInicioApuesta(null);
		gestionEvento.setFechaFinApuesta(null);
		this.idTipoApuesta=-1;
		this.nombreTipoApuestaSelected="";
		
	}

	public void setNombreTipoApuestaSelected(String nombreTipoApuestaSelected) {
		this.nombreTipoApuestaSelected = nombreTipoApuestaSelected;
	}

	public String getNombreTipoApuestaSelected() {
		return nombreTipoApuestaSelected;
	}

	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(ArrayList<Resultado> resultados) {
		this.resultados = resultados;
	}

	public void setNuevoResultado(Resultado nuevoResultado) {
		this.nuevoResultado = nuevoResultado;
	}

	public Resultado getNuevoResultado() {
		return nuevoResultado;
	}

	public void setIdTipoApuesta(Integer idTipoApuesta) {
		this.idTipoApuesta = idTipoApuesta;
	}

	public Integer getIdTipoApuesta() {
		return idTipoApuesta;
	}

	public void setResultadoAEliminar(Resultado resultadoAEliminar) {
		this.resultadoAEliminar = resultadoAEliminar;
	}

	public Resultado getResultadoAEliminar() {
		return resultadoAEliminar;
	}

}
