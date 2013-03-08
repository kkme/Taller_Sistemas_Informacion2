package managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import negocio.eventos.interfaces.IEventosAdmin;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import dominio.Evento;

import utiles.Constantes;


@ManagedBean(name="mapaBean")
@SessionScoped
public class MapaBean implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private Double latitud;
	private Double longitud;
	private MapModel emptyModel;
	@EJB(lookup="java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdmin eventosAdmin;
	
	 private MapModel listaEventos;  
	  
	 @PostConstruct
	    public void cargarEventosEnMapa() {  
	        listaEventos = new DefaultMapModel();  
	        
	        for(Evento e: eventosAdmin.obtenerTodosEventos() ){
 
	        LatLng coord1 = new LatLng(e.getUbicacion().getLatitud(), e.getUbicacion().getLongitud());
	        listaEventos.addOverlay(new Marker(coord1, e.getNombre()));  
	        }
 
	    }  
	  
	    public MapModel getListaEventos() {  
	        return listaEventos;  
	    } 
	
	
	
	
	
	
	

	public MapaBean () {
		emptyModel = new DefaultMapModel(); 
		latitud = Constantes.LATITUD_INICIAL;
		longitud = Constantes.LONGITUD_INICIAL;
		
		//Shared coordinates
		LatLng coord1 = new LatLng(latitud,longitud);
		Marker marker = new Marker(coord1, "Nuevo Evento");
		
		//Draggable
		emptyModel.addOverlay(marker);
		marker.setDraggable(true);
	}

	public void onMarkerDrag (MarkerDragEvent event) {
		LatLng latlng = event.getMarker().getLatlng();
		latitud = latlng.getLat(); 
		longitud = latlng.getLng();
	}
	
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}
}
				