package managedbeans;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import negocio.eventos.interfaces.IEventosAdmin;

import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import dominio.Evento;

@ManagedBean(name="gestionAgenda")
@ViewScoped
public class GestionAgenda {
	
	private ScheduleModel eventModel;
	private Evento eventoSelected;
	
	@EJB(lookup="java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdmin eventosAdmin;

	public void onEventSelect (ScheduleEntrySelectEvent selectEvent) {
		ScheduleEvent event = selectEvent.getScheduleEvent();
		eventoSelected = new Evento();
		Calendar dateInic = Calendar.getInstance();
		Calendar dateFin = Calendar.getInstance();
		dateInic.setTime(event.getStartDate());
		dateFin.setTime(event.getEndDate());
		eventoSelected.setNombre(event.getTitle());
		eventoSelected.setInicio(dateInic);
		eventoSelected.setFin(dateFin);
	}
	
	public ScheduleModel getEventModel() {
		eventModel = new DefaultScheduleModel(); 
		List<Evento> eventos = eventosAdmin.obtenerTodosEventos();
		for (Evento e: eventos) {
			DefaultScheduleEvent event = new DefaultScheduleEvent();
			event.setTitle(e.getCompeticion().getNombre() + " - " + e.getNombre());
			event.setStartDate(e.getInicio().getTime());
			event.setEndDate(e.getFin().getTime());
			event.setAllDay(false);
			eventModel.addEvent(event);
		}
		return eventModel;
	}
	
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
	
	public Evento getEventoSelected() {
		return eventoSelected;
	}
	
	public void setEventoSelected(Evento eventoSelected) {
		this.eventoSelected = eventoSelected;
	}
	
}
