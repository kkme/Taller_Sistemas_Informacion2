package managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dominio.Evento;
import dominio.TipoEvento;
import negocio.eventos.interfaces.IEventosAdmin;

@ManagedBean(name = "eventoController")
@SessionScoped
public class EventoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdmin eventosAdmin;

	public void setEventosAdmin(IEventosAdmin eventosAdmin) {
		this.eventosAdmin = eventosAdmin;
	}

	private Evento evento;
	private List<TipoEvento> tipoEventos; 

	public EventoController() {
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
		if(evento != null){
			tipoEventos = eventosAdmin.getResultadosEvento(evento.getId());
		}
	}
	
	public void onLupaClick(){
		System.out.println("la puta que te pario::" + evento.getNombre());
	}

	public Evento getEvento() {
		return evento;
	}

	public void setTipoEventos(List<TipoEvento> tipoEventos) {
		this.tipoEventos = tipoEventos;
	}

	public List<TipoEvento> getTipoEventos() {
		return tipoEventos;
	}
	
}
